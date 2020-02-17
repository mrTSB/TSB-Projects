module.exports = {
	name: 'ping',
	description: 'Ping!',
	cooldown: 5,
	execute(message) {
		message.channel.send(`Pong!`);
		//const msg = await message.channel.send(`🏓 Pinging....`)
		//mesg.edit(`🏓 Pong\nLatency is ${Math.floor(msg.createdAt - message.createAt)}\n API Latency ${Math.round(client.ping)}ms`)
	},
};
