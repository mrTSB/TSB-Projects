module.exports = {
	name: 'kick',
	description: 'Tag a member and kick them.',
	guildOnly: 'True',
	execute(message) {

		if (!message.guild) return;

		  const user = message.mentions.users.first();

		  if (user) {

			const member = message.guild.member(user);

			if (member) {

			  member.kick('Optional reason that will display in the audit logs').then(() => {

				message.reply(`Successfully kicked ${user.tag}`);
			  }).catch(err => {

				message.reply('I was unable to kick the member');

				console.error(err);
			  });
			} else {
			  message.reply('That user isn\'t in this guild!');
			}
		  } 
		
	  }
	//const taggedUser = message.mentions.users.first();
	//message.channel.send(`You wanted to kick: ${taggedUser.username}`);
};
