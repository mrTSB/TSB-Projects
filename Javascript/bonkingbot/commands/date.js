module.exports = {
	name: 'date',
	description: 'Fetches and replies date',
	cooldown: 60,
	execute(message) {
		var today = new Date();
		var date = (today.getMonth()+1)+'-'+today.getDate()+'-'+today.getFullYear();
		var time = today.getHours() + " hours, " + today.getMinutes() + " minutes, " + today.getSeconds() + " seconds";
		var dateTime = time+'   '+date;
		message.channel.send(dateTime);
	},
};
