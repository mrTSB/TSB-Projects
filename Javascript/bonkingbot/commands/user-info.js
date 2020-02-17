module.exports = {
	name: 'user-info',
	description: 'Display info about yourself.',
	execute(message) {
		message.channel.send(`Your username: ${message.author.username}\nYour ID: ${message.author.id}`);
		/*
		const embed = new Discord.RichEmbed()
		  .setTitle(`${message.author.username}'s Information`)
		  .setColor(0x00AE86)
		  .setTimestamp()
		  .setURL("https://discord.js.org/#/docs/main/indev/class/RichEmbed")
		  .addField(`Your username: ${message.author.username}`, `Your ID#: ${message.author.id}`)
		  .setImage(message.author.displayAvatarURL)
		  .setAuthor(message.author.username, message.author.displayAvatarURL)
		  .setFoother(client.user.username, client.user.displayAvatarURL)
		  message.channel.send(embed);
		*/  
	},
};
