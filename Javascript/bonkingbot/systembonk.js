//imports
const fs = require('fs');
const Discord = require('discord.js');
const { prefix, token, version } = require('./config.json');

//variables

const client = new Discord.Client();
client.commands = new Discord.Collection();

const commandFiles = fs.readdirSync('./commands').filter(file => file.endsWith('.js'));

for (const file of commandFiles) {
	const command = require(`./commands/${file}`);
	client.commands.set(command.name, command);
}

const cooldowns = new Discord.Collection();

//bot startup

client.once('ready', () => {
	console.log(`Hello, ${client.user.username} is now online!`);
	client.user.setPresence({
		status:"online",
		game:{
			name:"Being developed...",
			type:"watching"
		}
	})
});


/*
//Log all messages in command prompt

client.on('message', async message => {
	  //const channel = message.guild.channels.find(ch => ch.name === 'message-log');
		//if (!channel) return;
		//channel.send(`${message.author} said: ${message.content} in #${message.channel.name}`)
		console.log(`${message.author} said: ${message.content} in #${message.channel.name}`)
});
*/

// Create an event listener for new guild members

client.on('guildMemberAdd', member => {
  // Send the message to a designated channel on a server:
  const channel = member.guild.channels.find(ch => ch.name === 'welcome');
  // Do nothing if the channel wasn't found on this server
  if (!channel) return;
  // Send the message, mentioning the member
  channel.send(`Welcome to the server, ${member}`);
});

//command handler

client.on('message', message => {
	if (!message.content.startsWith(prefix) || message.author.bot) return;

	const args = message.content.slice(prefix.length).split(/ +/);
	const commandName = args.shift().toLowerCase();

	const command = client.commands.get(commandName)
		|| client.commands.find(cmd => cmd.aliases && cmd.aliases.includes(commandName));

	if (!command) return;

	if (command.guildOnly && message.channel.type !== 'text') {
		return message.reply('I can\'t execute that command inside DMs!');
	}

//feedback for args command

	if (command.args && !args.length) {
		let reply = `You didn't provide any arguments, ${message.author}!`;

	if (command.usage) {
			reply += `\nThe proper usage would be: \`${prefix}${command.name} ${command.usage}\``;
		}

		return message.channel.send(reply);
	}

//preparing cooldown

	if (!cooldowns.has(command.name)) {
		cooldowns.set(command.name, new Discord.Collection());
	}

//execute cooldown

	const now = Date.now();
	const timestamps = cooldowns.get(command.name);
	const cooldownAmount = (command.cooldown || 3) * 1000;

	if (timestamps.has(message.author.id)) {
		const expirationTime = timestamps.get(message.author.id) + cooldownAmount;

		if (now < expirationTime) {
			const timeLeft = (expirationTime - now) / 1000;
			return message.reply(`please wait ${timeLeft.toFixed(1)} more second(s) before reusing the \`${command.name}\` command.`);
		}
	}

	timestamps.set(message.author.id, now);
	setTimeout(() => timestamps.delete(message.author.id), cooldownAmount);

//catch error for commands with arguments

	try {
		command.execute(message, args);
	} catch (error) {
		console.error(error);
		message.reply('there was an error trying to execute that command!');
	}
});

//token

client.login(token);
