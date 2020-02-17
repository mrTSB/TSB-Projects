import random


class OtherOperations:
    def emoji_converter(self, message):
        words = message.split(" ")
        emojis = {
            ":)": "😀",
            ":(": "☹"
        }
        output = ""
        for word in words:
            output += emojis.get(word, word) + " "
        return output


class NumberOperations:
    def find_maximum(self, numbers):
        maximum = numbers[0]
        for number in numbers:
            if number>maximum:
                maximum = number
        print(maximum)


class Dice:
    def roll(self):
        x = random.randint(1,6)
        y = random.randint(1,6)
        return (x,y)