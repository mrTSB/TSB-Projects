module.exports = {
	name: 'role-info',
	description: 'Display info about a role.',
	execute(message, args) {
        const RoleEmbed = new Discord.RichEmbed()
        .setTitle("Role:" + args)
        .setAuthor(`${message.author.username}`)
    message.channel.send(RoleEmbed);
	},
};