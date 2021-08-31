'''
Created on 2020 Jul 27
---------------------------
[Program Description]
---------------------------
Author: Dennis Au
ID: 201421820
Email: auxx1820@mylaurier.ca
__updated__="2020-07-27"
---------------------------
'''
# Imports
from Faction import Faction

#used for sorting
def final_vp(fac: Faction):
    return fac.final_vp

class Game:

    def __init__(self, id_num: int, f1: Faction, f2: Faction, f3: Faction, f4:Faction):
        """
        -------------------------------------------------------
        Initializes a Game.
        Each Game has a set of factions.
        -------------------------------------------------------
        Parameters:
            f1 - faction in position 1
            f2 - faction in position 2
            f3 - faction in position 3
            f4 - faction in position 4
        """
        self.id_num = id_num
        self.factions = [] #list of faction objects
        self.factions.append(f1)
        self.factions.append(f2)
        self.factions.append(f3)
        self.factions.append(f4)
        self.factions.sort(reverse=True, key=final_vp) #sort the factions by winner to loser
        self.faction_set = set() #we use a set here for easy querying
        self.faction_set.add(f1.faction)
        self.faction_set.add(f2.faction)
        self.faction_set.add(f3.faction)
        self.faction_set.add(f4.faction)
        
    def __str__(self):
        return "ID:{}\n{}\n{}\n{}\n{}".format(self.id_num, self.factions[0], self.factions[1], self.factions[2], self.factions[3])
    
    
    