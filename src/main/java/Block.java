import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    //Phương thức khởi tạo khối
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    //Phương thức tính Hash
    public String calculateHash(){
        String calculatedhash = DigitalSignatures.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    //Phương thức đảo khối
    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0','0');
        while (!hash.substring(0,difficulty).equals(target)){
            nonce ++;
            hash = calculateHash();

        }
        System.out.println("Block Mined!!! : " + hash);
    }

}