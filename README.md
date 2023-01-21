# Blockchain implementation in Java from Scratch

To run the project install
- OpenJDK 19
- JetBrains IntelliJ IDEA

Sample Output
```json
Block Chain: 
[
  {
    "hash": "000000f754d6ddbecb5b1a95a58ba2669bb00523ab61fc5adb2a969b29ffdecd",
    "previousHash": "0",
    "blockData": "First block on the chain",
    "timeStamp": 1674290202811,
    "nonce": 3322125
  },
  {
    "hash": "0000037ef19bd39e04b83b614ae3ff88f26dda129f1948fc7ac91939295cf7f3",
    "previousHash": "000000f754d6ddbecb5b1a95a58ba2669bb00523ab61fc5adb2a969b29ffdecd",
    "blockData": "Second block on the chain",
    "timeStamp": 1674290209752,
    "nonce": 2525178
  },
  {
    "hash": "00000df114349d9fede838566f7204430e0e618b8ab751c4002dacf8714332cb",
    "previousHash": "0000037ef19bd39e04b83b614ae3ff88f26dda129f1948fc7ac91939295cf7f3",
    "blockData": "Third block on the chain",
    "timeStamp": 1674290213522,
    "nonce": 423708
  },
  {
    "hash": "000004b786c31544a6eaf336352ea7c43cdd24ebe09a4b0f44bcd81f76f48208",
    "previousHash": "00000df114349d9fede838566f7204430e0e618b8ab751c4002dacf8714332cb",
    "blockData": "Fourth block on the chain",
    "timeStamp": 1674290214143,
    "nonce": 143205
  },
  {
    "hash": "000009467d0eee7bc7494fd4d9fe2cacf4f742ada781499db3782137fa47caff",
    "previousHash": "000004b786c31544a6eaf336352ea7c43cdd24ebe09a4b0f44bcd81f76f48208",
    "blockData": "Fifth block on the chain",
    "timeStamp": 1674290214353,
    "nonce": 196630
  }
]
```
Output of Transactions on Blockchain
```bash
Creating and Mining Genesis Block
Transaction successfully added to the chain
Block Mined!!!, Hash of new block : 0000017c20a99675dcacf4e9ed6c5732061d7c6070b2c260c27a101433c63c4b

WalletA's balance is: 100.0

WalletA is trying to send funds 40.0 to wallet B....
Transaction successfully added to the chain
Block Mined!!!, Hash of new block : 00000d357269c4ffdac857cae544a12f40eb05416ef4a2a90d27c4f7dbc6fd10

Wallet A's balance is: 60.0
Wallet B's balance is: 40.0

WalletA is trying to send funds 1000.0 to wallet B....
Which it dosen't have
Not enough funds to send
Block Mined!!!, Hash of new block : 00000ae7353831e1ae7b1f542759cf416dbf0a106b4a2f124e15c309e3a8ea8c

Wallet A's balance is: 60.0
Wallet B's balance is: 40.0

WalletA is trying to send funds 20.0 to wallet B....
Transaction successfully added to the chain
Block Mined!!!, Hash of new block : 00000b5866170ae149bc89aa138e37bc96eeb93c00ba67356f98f6f8f11f929f

Wallet A's balance is: 40.0
Wallet B's balance is: 60.0

Process finished with exit code 0
```