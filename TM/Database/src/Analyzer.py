'''
Created on 2020 Sep 6
---------------------------
[Program Description]
---------------------------
Author: Dennis Au
ID: 201421820
Email: auxx1820@mylaurier.ca
__updated__="2020-09-06"
---------------------------
'''
from Faction import Faction
# Imports
from Game import Game
import tkinter

#Games after ID 49 are played with landscapes on.
class Analyzer:
    def __init__(self, f):
        self.database = list()  # list of games
        self.games_sheet = open(f)  # a file object

    def line_to_game(self, line: str):
        """Takes a line of string and creates a game object.
        Returns: game - Game"""
        #         game = Game()
        data = line.split()  # data is a list of data strings
        id_num = data.pop(0)  # remove the unique identifier from each row first
        f1 = Faction(data.pop(0), int(data.pop(0)), int(data.pop(0)))
        f2 = Faction(data.pop(0), int(data.pop(0)), int(data.pop(0)))
        f3 = Faction(data.pop(0), int(data.pop(0)), int(data.pop(0)))
        f4 = Faction(data.pop(0), int(data.pop(0)), int(data.pop(0)))
        game = Game(id_num, f1, f2, f3, f4)
        return game

    def update(self):
        """updates self.database using self.games_sheet"""
        for line in self.games_sheet:
            game = self.line_to_game(line)
            self.database.append(game)
        return

    def display(self):
        """displays all the games in this database"""
        for game in self.database:
            print(game)
        return

    def find_games(self, set_of_games: set):
        """Returns a list of all games in the database with factions contained in the set"""
        result = []
        for game in self.database:
            if set_of_games.issubset(game.faction_set):
                result.append(game)
        return result

    def average_gain(self, fac: str, lst: list):
        """Returns the average total gain of faction fac out of all the games in List lst"""
        total = 0
        count = 0
        avg = 0;
        for game in lst:
            for fact in game.factions:
                if fact.faction == fac:
                    total += fact.total_gain()
                    count += 1
        if count > 0:
            avg = total / count
        return avg

    def analyze(self, set_of_games: set):
        resulting_lst = a.find_games(set_of_games)
        for game in resulting_lst:
            print(game)
            print()
        if len(resulting_lst) == 0:
            print("No games found.")
        print("Average information of " + str(len(resulting_lst)) + " game(s):")
        for fac in set_of_games:
            avg = self.average_gain(fac, resulting_lst)
            print(fac + "'s average TG: " + str(avg))


# m = tkinter.Tk()
# tkinter.Label(m, text='First Name').grid(row=0)
# tkinter.Label(m, text='Last Name').grid(row=1)
# e1 = tkinter.Entry(m)
# e2 = tkinter.Entry(m)
# e1.grid(row=0, column=1)
# e2.grid(row=1, column=1)
# m.mainloop()

a = Analyzer("games.txt")
# line = (a.games_sheet.readline())
# print(a.line_to_game(line))
a.update()
a.analyze({"ME", "HA", "DA", "AU"})

