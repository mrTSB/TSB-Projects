const Discord = require('discord.js');

module.exports = {
	name: 'bot-info',
	description: 'information on the bot',
	cooldown: 5,
	execute(message) {
		const embed = new Discord.RichEmbed()
		  .setTitle("BonkingBot Bot Information")
		  .setAuthor("bonkingvillan#0001", "https://i.imgur.com/YGBpkhM.png")

		   // Alternatively, use "#00AE86", [0, 174, 134] or an integer number.

		  .setColor(0x00AE86)
		  .setDescription("This is the BonkingBot, a useful moderation and system bot with a currency system under development.")
		  .setFooter("created by bonkingvillan#0001", "https://i.imgur.com/YGBpkhM.png")

		  //.setImage("https://imgur.com/gallery/zaPVma6")
		  //.setThumbnail("http://i.imgur.com/p2qNFag.png")

		   // Takes a Date object, defaults to current date.

		  .setTimestamp()
		  .setURL("https://discord.js.org/#/docs/main/indev/class/RichEmbed")
		  .addField("Confused about what commands are available? Don't know how to use a command?",
		    "Type !help in the bot's dms or in your server to recieve immediate assistance in your dms.")

				//Inline fields may not display as inline if the thumbnail and/or image is too big.

		  .addField("Version #", "1.0.0", true)

		   // Blank field, useful to create some space.

		  .addBlankField(true)
		  .addField("Make sure to join the BonkingBot support server!", "https://discord.gg/vmGeX2d", true);

		  message.channel.send(embed);
	},
};
