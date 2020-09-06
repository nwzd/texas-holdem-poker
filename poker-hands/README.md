## Ranking Texas Hold'em Hands
Rank hands comparing them to the board and sort them by rank in ascending order

For example for a board `4cKs4h8s7s` and hands `Ad4s Ac4d As9s KhKd 5d6d`
output of the program will be `Ad4s=Ac4d 5d6d As9s KhKd`

#### Clone 
`git clone https://github.com/nwzd/texas-holdem-poker.git`

#### Navigate folder /texas-holdem-poker/poker-hands and compile the project 
`mvn clean install`

#### Execute some examples
`java -jar target/poker-hands-0.0.1-SNAPSHOT.jar 4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d`

## Known Bugs
1. Comparing A2345 to 34567
2. Regex for input format. Should throw exception if arguments does not follow the format.