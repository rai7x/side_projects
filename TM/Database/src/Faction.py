'''
Created on 2020 Jul 31
---------------------------
[Program Description]
---------------------------
Author: Dennis Au
ID: 201421820
Email: auxx1820@mylaurier.ca
__updated__="2020-07-31"
---------------------------
'''

# Imports

class Faction:
    def __init__(self, faction: str, starting_vp: int, final_vp: int):
        self.faction = faction
        self.starting_vp = starting_vp
        self.final_vp = final_vp

    def __str__(self):
        return "{}:{}-{} Total Gain: {}".format(self.faction, self.starting_vp, self.final_vp,
                                                self.total_gain())
    
    def __eq__(self, fac):
        result = self.faction == fac.faction
        return result
        
    def __gt__(self, fac):
        result = self.faction > fac.faction
        return result
    
    def __lt__(self, fac):
        result = self.faction < fac.faction
        return result

    def total_gain(self):
        result = self.final_vp - self.starting_vp
        return result
    
# f1 = Faction("SW", 15, 130, 1)
# f2 = Faction("NO", 15, 130, 1)
# print(f1)
# print(f2)
# print(f1>f2)
# f3 = Faction()
# print(f3)