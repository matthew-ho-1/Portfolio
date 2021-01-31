const { time } = require('console');
const Discord = require('discord.js');
const client = new Discord.Client();
const fs = require("fs");
client.stats = require("./stats.json");

client.once('ready', () => { 
	console.log('Ready!');
	client.user.setActivity('YOU (V1.0)',   {type: "WATCHING"});
});

client.on('message', message => {
  	var listOfWords = ["rude", "donald trump", "idiot", "stfu", "narcisstic", "fault", "fight", "bully", "pathetic","hate", "stupid", "dumb", "annoying", "disaster", "monster", "disgrace", "immature", "abuse", "fault", "unban", "kick"];
	var timesPunished; var isAdmin = false; var isMod = false; var isGraduate = false; var isSenior = false; var isJunior = false; var isMiddle = false;
	var isVisit = false; var isVal = false; var isLol = false; var isMine = false; var isRob = false; var isChange = false;
	var adminRole; var modRole; var gradRole; var seniorRole; var juniorRole; var middleRole; var visitRole; var valRole; var lolRole; var mineRole; var robRole; var changeRole;
	var isTest = false; var testRole; 
	var visitedOnce = false;

			for(let i = 0; i < listOfWords.length; i++) {
				if(message.content.toLowerCase().includes(listOfWords[i]) && !visitedOnce && message.author.id !== "259156661199962112")
				{
					let phrase = message.content;
					visitedOnce = true;
					let timeoutRole = message.guild.roles.cache.find(role => role.name === "timeout");
					let personRole = message.member.roles.cache;

					message.member.roles.add(timeoutRole).catch(console.error);  
					if(personRole.has("804039630412185661")){
						isTest = true;
						testRole = message.guild.roles.cache.find(role => role.name === "test");
						message.member.roles.remove(testRole).catch(console.error);
					}
					if(personRole.has("758099099630305321")){
						isAdmin = true;
						adminRole = message.guild.roles.cache.find(role => role.name === "admin");
						message.member.roles.remove(adminRole).catch(console.error);
					}
					if(personRole.has("804006013220028447")){
						isMod = true;
						modRole= message.guild.roles.cache.find(role => role.name === "Moderator");
						message.member.roles.remove(modRole).catch(console.error);
					}
					if(personRole.has("763456851765100609")){
						isGraduate = true;
						gradRole = message.guild.roles.cache.find(role => role.name === "graduate");
						message.member.roles.remove(gradRole).catch(console.error);
					 }
					if(personRole.has("746549792049594388")){ 
						isSenior = true;
						seniorRole = message.guild.roles.cache.find(role => role.name === "senior high");
						message.member.roles.remove(seniorRole).catch(console.error);
					}
					if(personRole.has("746549722138935416")){
						isJunior = true;
						juniorRole = message.guild.roles.cache.find(role => role.name === "junior high");
						message.member.roles.remove(juniorRole).catch(console.error);
					}
					if(personRole.has("746549656208670784")){
						isMiddle = true;
						middleRole = message.guild.roles.cache.find(role => role.name === "middle school");
						message.member.roles.remove(middleRole).catch(console.error);
					}
					if(personRole.has("724460733647028304")){
						isVal = true;
						valRole = message.guild.roles.cache.find(role => role.name === "Valorant");
						message.member.roles.remove(valRole).catch(console.error);
					}
					if(personRole.has("724460828635430962")){
						isLol = true;
						lolRole = message.guild.roles.cache.find(role => role.name === "League");
						message.member.roles.remove(lolRole).catch(console.error);
					}
					if(personRole.has("724460907404591105")){
						isRob = true;
						robRole = message.guild.roles.cache.find(role => role.name === "Roblox");
						message.member.roles.remove(robRole).catch(console.error);
					}
					if(personRole.has("724460964526686209")){
						isMine = true;
						mineRole = message.guild.roles.cache.find(role => role.name === "Minecraft");
						message.member.roles.remove(mineRole).catch(console.error);
					}
					if(personRole.has("724685692189540362")){
						isChange = true;
						changeRole = message.guild.roles.cache.find(role => role.name === "change nickname");
						message.member.roles.remove(changeRole).catch(console.error);
					}
					if(personRole.has("726985933974536194")){
						isVisit = true;
						visitRole = message.guild.roles.cache.find(role => role.name === "visiter");
						message.member.roles.remove(visitRole).catch(console.error);
					}
					let json = JSON.parse(JSON.stringify(client.stats));
					if ((message.author.username in json) == false) {
						client.stats [message.author.username] = {
							numberOfTimes: 1
						}
					}
					else { 
						client.stats [message.author.username] = {
							numberOfTimes: ++client.stats[message.author.username].numberOfTimes,
						}
					}
					fs.writeFile("./stats.json", JSON.stringify(client.stats, null, 4), err => {
						if (err) throw error
					});
					timesPunished = client.stats[message.author.username].numberOfTimes;
					let sendUser = message.author;
					function removeFromRole() {
						message.member.roles.remove(timeoutRole).catch(console.error);
					}
					function addRolesBack() {
						if(isTest)
							message.member.roles.add(testRole).catch(console.error); 
						if(isAdmin)
							message.member.roles.add(adminRole).catch(console.error); 
						if(isMod)
							message.member.roles.add(modRole).catch(console.error);
						if(isGraduate)
							message.member.roles.add(gradRole).catch(console.error); 
						if(isSenior)
							message.member.roles.add(seniorRole).catch(console.error);
						if(isJunior)
							message.member.roles.add(juniorRole).catch(console.error); 
						if(isMiddle)
							message.member.roles.add(middleRole).catch(console.error); 
						if(isVal)
							message.member.roles.add(valRole).catch(console.error); 
						if(isLol)
							message.member.roles.add(lolRole).catch(console.error); 
						if(isRob)
							message.member.roles.add(robRole).catch(console.error); 
						if(isMine)
							message.member.roles.add(mineRole).catch(console.error); 
						if(isChange)
							message.member.roles.add(changeRole).catch(console.error); 
						if(isVisit)
							message.member.roles.add(visitRole).catch(console.error); 
					}
					let punished = message.author.username;
					let punishedID = "<@" + message.author.id + ">";
					switch(timesPunished) {
						case 1: let dateC = new Date();
								dateC.setHours(dateC.getHours() + 1);
								sendUser.send(punished + ", you are on timeout for 1 hour for violation of server rules. **Next offense: 6 hour timeout.** Your roles will be automatically given back to you at **" + dateC.toLocaleString() + "**. You cannot respond to the bot as no one will see it. If you want to dispute this offense, share your questions, thoughts, concerns, or if you were wrongly placed in this offense, please DM Matt Ho (mathematicalmatt01#5062). Additionally if you have any bug reports or any ways to improve the bot please DM Matt. ");
								message.channel.send(punishedID + " has been put on timeout for 1 hour.")
								client.users.fetch("259156661199962112").then(user => { user.send(punished +" has been put on timeout for 1 hour. They will be released at " + dateC.toLocaleString() + ". The phrase in question is: " + phrase)})
								setTimeout(removeFromRole,3600000);
								addRolesBack();
								break;
						case 2: let dateD = new Date();
								dateD.setHours(dateD.getHours() + 6);
								sendUser.send(punished + ", you are on timeout for 6 hours. **Next offense: 12 hour timeout.** Your roles will be automatically given back to you at **" + dateD.toLocaleString() + "**. You cannot respond to the bot as no one will see it. If you want to dispute this offense, share your questions, thoughts, concerns, or if you were wrongly placed in this offense, please DM Matt Ho (mathematicalmatt01#5062). Additionally if you have any bug reports or any ways to improve the bot please DM Matt. ");
								message.channel.send(punishedID + " has been put on timeout for 6 hours.")
								client.users.fetch("259156661199962112").then(user => { user.send(punished + " has been put on timeout for 6 hours. They will be released at " + dateD.toLocaleString() + ". The phrase in question is: " + phrase)})
								setTimeout(removeFromRole,21600000);
								addRolesBack();
								break;
						case 3: let dateE = new Date();
								dateE.setHours(dateD.getHours() + 12);
								sendUser.send(punished + ", you are on timeout for 12 hours. **Next offense: 1 day timeout.** Your roles will be automatically given back to you at **" + dateE.toLocaleString() + "**. You cannot respond to the bot as no one will see it. If you want to dispute this offense, share your questions, thoughts, concerns, or if you were wrongly placed in this offense, please DM Matt Ho (mathematicalmatt01#5062). Additionally if you have any bug reports or any ways to improve the bot please DM Matt. ");
								message.channel.send(punishedID + " has been put on timeout for 12 hours.")
								client.users.fetch("259156661199962112").then(user => { user.send(punished + " has been put on timeout for 12 hours. They will be released at " + dateE.toLocaleString() + ". The phrase in question is: " + phrase) })
								setTimeout(removeFromRole,43200000);
								addRolesBack();
								break;
						case 4: let dateF = new Date();
								dateF.setDate(dateF.getDate() + 1);
								sendUser.send(punished + ", you are on timeout for 1 day. **Next offense: Temporarily kicked from the server for 1 day.** Your roles will be automatically given back to you on **" + dateF.toLocaleString() + "**. You cannot respond to the bot as no one will see it. If you want to dispute this offense, share your questions, thoughts, concerns, or if you were wrongly placed in this offense, please DM Matt Ho (mathematicalmatt01#5062). Additionally if you have any bug reports or any ways to improve the bot please DM Matt. ");
								message.channel.send(punishedID + " has been put on timeout for 1 day.")
								client.users.fetch("259156661199962112").then(user => { user.send(punished + " has been put on timeout for 1 day. They will be released at " + dateF.toLocaleString() + ". The phrase in question is: " + phrase) })
								setTimeout(removeFromRole,86400000);
								addRolesBack();
								break;
						case 5: let dateA = new Date();
								dateA.setDate(dateA.getDate() + 1);
								sendUser.send(punished + ", you have temporarily been kicked from the server for 1 day. **Next offense: Temporarily kicked from the server for 2 days.** **Do not try and rejoin the server, you will move to the next offense if you do.** Matt will reinvite you back on **" + dateA.toLocaleString() + "**. You cannot respond to the bot as no one will see it. If you want to dispute this offense, share your questions, thoughts, concerns, or if you were wrongly placed in this offense, please DM Matt Ho (mathematicalmatt01#5062). Additionally if you have any bug reports or any ways to improve the bot please DM Matt. ").then(function(){
									message.member.kick({reason: "Violation of server rule 1: Being respectful, result in kick."})
								}).catch(function(){
									message.member.kick({reason: "Violation of server rule 1: Being respectful, result in kick."})
								});
								message.channel.send(punished + " has been temporarily kicked from the server for 1 day.")
								client.users.fetch("259156661199962112").then(user => { user.send(punished + " has been temporarily kicked from the server for 1 day. Time to reinvite: " + dateA.toLocaleString() + ". The phrase in question is: " + phrase)})
								break;
						case 6: sendUser.send(punished + ", you have temporarily been kicked from the server for 2 days. **Final Warning before you are banned from the server**. Do not try and rejoin the server, you will move to the next offense if you do. Matt will reinvite you back on **" + dateB.toLocaleString() + "**. You cannot respond to the bot as no one will see it. If you want to dispute this offense, share your questions, thoughts, concerns, or if you were wrongly placed in this offense, please DM Matt Ho (mathematicalmatt01#5062). Additionally if you have any bug reports or any ways to improve the bot please DM Matt. ").then(function(){
									message.member.kick({reason: "Violation of server rule 1: Being respectful, result in kick."})
								}).catch(function(){
									message.member.kick({reason: "Violation of server rule 1: Being respectful, result in kick."})
								});
								let dateB = new Date();
								dateB.setDate(dateB.getDate() + 1);
								message.channel.send(punished + " has been temporarily kicked from the server for 2 days.")
								client.users.fetch("259156661199962112").then(user => { user.send(punished + " has been temporarily kicked from the server for 2 days. Time to reinvite: " + dateB.toLocaleString() + ". The phrase in question is: " + phrase) })
								break;
						case 7: sendUser.send(punished + ", you are in violation of server rules. You have been banned from the server. Do not attempt to make another account, as that will also be banned. You cannot respond to the bot as no one will see it. If you want to dispute this offense, share your questions, thoughts, concerns, or if you were wrongly placed in this offense, please DM Matt Ho (mathematicalmatt01#5062). Additionally if you have any bug reports or any ways to improve the bot please DM Matt. ").then(function(){
									message.member.ban({reason: `Violaton of server rule 1`})
								}).catch(function(){
								message.member.ban({reason: `Violaton of server rule 1`})
								});  
								message.channel.send(punished + " has been banned from the server.")
								client.users.fetch("259156661199962112").then(user => { user.send(punished + " has been banned from the server. The phrase in question is: " + phrase) })
								break;
					}
				}
				else {
					continue;
				}
			}
});

client.login('[REDACTED: INSERT BOT TOKEN HERE');
