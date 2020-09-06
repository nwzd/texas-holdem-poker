# texas-holdem-poker
7 cards rating in Texas Hold'em Poker. Rank hands comparing to the board and sort them by rank in ascending order

For example 

Board: 4cKs4h8s7s
Hands: Ad4s Ac4d As9s KhKd 5d6d
Output: Ad4s=Ac4d 5d6d As9s KhKd

# Execution process

## Clone 
git clone https://github.com/nwzd/texas-holdem-poker.git

## Navigate folder /texas-holdem-poker/poker-hands and compile the project 
mvn clean install

## Execute some examples
java -jar target/poker-hands-0.0.1-SNAPSHOT.jar 4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d
