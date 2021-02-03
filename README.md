# GFVC_BOY
###### Geek Virtual Fitness Challenge  
### Batiste Oscar Yassine -- projet AIA

## Description de l'API

### Modèle


##### User
Représentation des utilisateur. Un user possède des informations de connexion (login, mot de passe) et un ensemble de challenges auquel il est inscrit.

##### Challenge
Un challenge contient une liste de points de passages et de segments. Il contient également un ensemble de joueurs qui y participent.

##### Point de passage
Ce sont les checkpoints. Ils contiennent chacun deux segments (lent et rapide) pour se rendre au point de passage suivant.

##### Segment
Ils contiennent une liste d'obstacles que le joueur devra passer.

##### Suggestion
Elle est liée à un user et possède une description. Les joueurs peuvent voter pour ou contre.



### Services web


##### Vitrine
Ce sont les méthodes publics. Elles sont accessibles sans authentifications. L'utilisateur peut avoir un aperçu des challenges.

##### Player
Ce sont les méthodes accessibles par un utilisateur connecté. Il peut gérer son profil, s'inscrire à des challenges et voter.

##### Admin
Ce sont les méthodes accessibles par les administrateurs. On peut gérer les challenges, les utilisateurs et les votes.
