module.exports = {
	name: 'server-info',
	description: 'Display info about this server.',
	execute(message) {
		message.channel.send(`Server name: ${message.guild.name}\nTotal members: ${message.guild.memberCount}`);
	},
};
