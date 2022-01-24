# Jeu d'aventure texte

L'objectif de ce projet est de créer un [jeu d'aventure texte](https://fr.wikipedia.org/wiki/Jeu_vid%C3%A9o_textuel) en ligne de commandes. Le jeu doit décrire au joueur ce que son personnage voit et ce qui se passe autour de lui; le joueur doit entrer des commandes afin de se déplacer et d'agir sur son environnement.

## Mission 1: lieux et navigation

Avant toute chose, il faut que le jeu décrive au joueur où il se trouve, et qu'il puisse se déplacer d'un endroit à l'autre. Le déroulement du jeu pourrait ressembler à l'exemple suivant:

<details>
<summary>Exemple</summary>

> You are in the bedroom. West is the bathroom, north is the corridor.

`west`

> You are in the bathroom. East is the bedroom.

`west`

> You cannot go into that direction!

> You are in the bathroom. East is the bedroom.

`east`

> You are in the bedroom. West is the bathroom, north is the corridor.

</details>

Afin d'obtenir ce résultat, implémenter les classes ci-après en suivant les spécifications fournies.

### `Room`

- Représente un lieu dans lequel le joueur peut se trouver.

| Méthode | Description |
|---|---|
| _**String** getName()_ | Renvoie le nom du lieu (exemple: `"bedroom"`) |
| _**Room** getRoomInDirection(**Direction** direction)_ | Renvoie le lieu où l'on arrive lorsque l'on part de ce lieu et qu'on emprunte la direction passée en paramètre (exemple: depuis la chambre à coucher, en passant la direction ouest, on devrait obtenir la salle de bain) |

### `Direction`

- Représente une direction que le joueur peut emprunter pour se déplacer d'un lieu à l'autre.

| Méthode | Description |
|---|---|
| _**String** getName()_ | Renvoie le nom de la direction (exemple: `"north"`) |

### `Game`

- Représente une partie jouée par le joueur.

| Méthode | Description |
|---|---|
| _**void** setup()_ | Initialise la partie en créant les objets de l'univers (les lieux et les directions) et en les associant les uns aux autres de la manière adéquate, et détermine le lieu de départ |
| _**void** update()_ | Décrit un cycle d'exécution de la partie: décrire le lieu courant, attendre une saisie de l'utilisateur, vérifier qu'elle correspond à une direction, changer de lieu si cette direction est empruntable depuis le lieu dans lequel on se trouve actuellement |
| _**boolean** isRunning()_ | Permet de savoir si la partie est en cours (`true`) ou si elle est terminée (`false`) |
| _**Room** getCurrentRoom()_ | Renvoie le lieu dans lequel le joueur se trouve actuellement |

### `App`

- Point d'entrée de l'application.

| Méthode | Description |
|---|---|
| _**static void** main(**String** args)_ | Processus principal. Crée une nouvelle partie et l'initialise, puis lui demande de réaliser un cycle d'exécution tant qu'elle est en cours. |

## Mission 2: objets et interactions

Maintenant que nos joueurs sont capables de se déplacer d'un lieu à une autre, il faudrait ajouter des éléments (objets, personnages, monstres…) avec lesquels ils pourront interagir.

<details>
<summary>Exemple</summary>

> You are in the bedroom. West is the bathroom, north is the corridor. There is a bed and a mirror.

`use bed`

> You take a quick nap. You feel refreshed!

`use mirror`

> You see your reflection. Looking good!

`open mirror`

> This does not open!

`talk to mirror`

> Silence...

</details>

### 1. Intégrer des objets à l'univers

- Écrire une classe `Item`, qui représente les éléments interactifs de l'univers.
- Chaque élément doit avoir un nom.
- Chaque pièce peut contenir une quantité indéterminée d'éléments. La liste des éléments visibles doit être affichée automatiquement dans chaque pièce.

### 2. Interagir avec des éléments

- Écrire une classe `Command` qui représente une commande que l'utilisateur peut entrer dans la console.
- Chaque commande doit avoir un texte par défaut qui s'affichera si jamais l'utilisateur tente de l'utiliser avec un élément qui n'a pas été prévu pour (exemple: `talk to mirror`).
- Chaque élément peut réagir à un nombre indéterminé de commandes. Dans un premier temps, utiliser une commande particulière avec un élément particulier doit produire l'affichage d'un texte particulier.

### 3. Programmer des interactions complexes

Utiliser une commande sur un élément doit pouvoir produire une variété d'effets, dont afficher un texte n'est qu'un exemple.

Implémenter une ou plusieurs des classes suivantes:

| Classe | Description |
|---|---|
| **MessageEffect** | Produit l'affichage d'un message dans la console. |
| **EndGameEffect** | Termine la partie en cours. |
| **RenameItemEffect** | Change le nom d'un élément donné de manière permanente. |
| **RemoveItemEffect** | Supprime un élément donné de la pièce dans laquelle il apparaît de manière permanente. |

- Chaque élément peut réagir à chaque commande en utilisant l'un des effets proposés ci-dessus (au lieu de simplement afficher un message comme précédemment demandé).
- BONUS: Chaque élément peut réagir à chaque commande en utilisant une série d'effets, au lieu d'un seul effet.

#### Exemples d'interactions à implémenter

- Manger le biscuit sur la table de la cuisine (`eat cookie`) doit produire sa disparition.
- Ouvrir le tiroir du bureau dans la chambre (`open drawer`) doit provoquer la réalisation que le tiroir est vide, et son nom doit changer en conséquence (`empty drawer`).
- Toucher une prise électrique (`touch plug`) doit produire la mort du héros, et donc la fin de la partie.

Si le bonus de l'étape 3 a été réalisé, chaque interaction doit être accompagnée d'au moins un message décrivant l'effet obtenu.

## Mission 3: gestion d'états

Nous avons presque tout ce qu'il faut pour faire un véritable jeu avec des éléments à débloquer au fur et à mesure. Néanmoins, il manque actuellement des états à nos éléments; c'est-à-dire que chaque élément posséde uniquement les propriétés qui lui ont été attribuées à la création du jeu, et qu'il n'a pas de propriété qui a vocation à être modifiée en cours de jeu.

### 1. Ajouter des états

Créer des classes **BooleanState**, **NumberState**, **StringState**… capables de contenir une valeur de chaque type. Chaque élément du jeu (**Item**) doit pouvoir avoir une série d'états (par exemple, pour une fenêtre: ouvert/fermé, pour un appareil à piles: le nombre de fois qu'il a été utilisé, pour un personnage: "inconnu" tant qu'on n'a pas appris son vrai nom…).

### 2. Manipuler les états

Créer des classes d'effets permettant de modifier la valeur d'un état précis. Ainsi, lorsque l'on utilise une commande sur un élément, l'un des effets possibles doit être de modifier un état (de l'élément qui a répondu à la commande, ou d'une autre).

### 3. Créer une classe d'état générique

Comme les différentes classes d'état ont le même fonctionnement, hormis le type de valeur qu'ils contiennent, les refactoriser sous forme d'une seule [classe générique](https://docs.oracle.com/javase/tutorial/java/generics/types.html), et adapter les autres classes en conséquence.

## ☕ Pause refactorisation

### Rendre les commandes autonomes

N'est-ce pas en réalité la responsabilité de chaque commande de déterminer si une saisie de l'utilisateur lui correspond?

<details>
<summary>Spoiler</summary>
Oui!
</details>

Implémenter une méthode _**String** match(**String** userInput)_ dans la classe **Command**. Cette méthode doit examiner la saisie utilisateur passée en paramètre et renvoyer:

- le reste de la saisie (le texte qui suit la commande) en cas de correspondance;
- ou **null** si la saisie ne correspond pas à la commande.

### Unifier les commandes

Après tout, les directions sont aussi des commandes, non?

<details>
<summary>Spoiler</summary>
Oui!
</details>

Implémenter une classe **StandAloneCommand** capable de produire une correspondance avec le nom de la commande (sans argument), et une classe **ArgumentCommand** capable de produire une correspondance avec le nom de la commande suivi d'autre chose. Les directions doivent être un cas particulier de **StandAloneCommand**. Les commandes qui permettent d'interagir avec les objets doivent être des **ArgumentCommand**.

### Unifier les commandes (bis)

Finalement, changer de lieu n'est jamais qu'un effet associé à une commande comme un autre, pas vrai?

<details>
<summary>Spoiler</summary>
Oui!
</details>

Implémenter une classe **ChangeRoomEffect** permettant de produire le changement de lieu lors de son déclenchement. Puis, implémenter une méthode _**List<Effect>** getEffects()_ dans la classe **StandAloneCommand** capable de renvoyer une liste contenant au moins un objet **ChangeRoomEffect**.

## 🤔 Pour se prendre la tête en attendant la prochaine fois…

- Comment pourrait-on implémenter des structures de contrôle (conditions, boucles…) dans les effets associés aux différentes commandes? Et comment pourrait-on les intercaler dans les listes d'effets existantes?
- Comment pourrait-on implémenter des changements d'état relatifs (c'est-à-dire, qui se basent sur la valeur actuelle de l'état au lieu de le remplacer complètement par une nouvelle valeur)? Par exemple, ajouter 1 ou retirer 1 à la valeur actuelle, au lieu de la remplacer par 1.
- Comment pourrait-on implémenter des opérateurs spécifiques à chaque type d'état? Par exemple, des opérateurs logiques (&&, ||, ...) pour les valeurs booléennes, des opérateurs arithmétiques (+, -, ...) pour les nombres, etc. Et ce, idéalement en conservant la classe générique **State<T>**?
- Comment pourrait-on sauvegarder une partie afin que l'état du jeu ne soit pas perdu lorsqu'on quitte l'application, et qu'on puisse le retrouver plus tard? Où et comment les informations de chaque partie pourraient-elles être stockées?

## Mission 4: créer la base de données

Plutôt que de créer manuellement les éléments de l'univers dans la méthode **setup()** de **Game**, il serait intéressant de pouvoir les créer automatiquement à partir du contenu d'une base de données.

- Créer le schéma de base de données à l'aide d'un outil de gestion de base de données (PHPMyAdmin, Adminer…) en se basant sur le diagramme de classes de l'application: https://lucid.app/lucidchart/invitations/accept/inv_3e03fc78-d8a0-4e71-adce-ec3aa38b10eb?viewport_loc=-384%2C-21%2C1985%2C851%2C0_0
- Peupler la base de données avec les exemples actuellement présents dans le code Java.
- Écrire les requêtes SQL permettant de répondre aux opérations exécutées par l'application (partant d'un lieu A et empruntant une direction B, dans quel lieu doit-on arriver? connaissant un élément A et une commande B, quels effets cela doit-il produire? etc…)

## Mission 5: lire la base de données

Maintenant que la base de données est créée, il faut adapter le code Java afin de faire correspondre les classes aux tables.

- Ajouter Hibernate comme dépendance à l'application.
- Adapter le code des modéles afin de configurer Hibernate correctement.
- Écrire des méthodes permettant de récupérer un élément de la base de données en fonction de son identifiant, et vérifier qu'elles fonctionnent correctement.

## Mission 6: ajouter un éditeur de lieux

Dans le fichier **Editor.java**, coder une application en ligne de commandes permettant d'obtenir la liste de tous les lieux existants dans la base de données, de créer des nouveaux lieux, modifier et supprimer des lieux existants.

### BONUS

Essayer d'appliquer les bonnes pratiques de programmation orientée objet étudiée en cours afin d'obtenir un code bien structuré et maintenable.
