public class TransactionInput {
    public String transactionOutputId;

    // In cryptocurrencies the wallet balance is just your
    // Unspent transaction output i.e. UTXO
    public TransactionOutput UTXO;

    public TransactionInput(String transactionOutputId){
        this.transactionOutputId = transactionOutputId;
    }
}
