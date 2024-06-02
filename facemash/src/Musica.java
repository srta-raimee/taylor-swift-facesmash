public class Musica extends AbstractMusica implements IMusica {
    private String nome;
    private String imagem;
    private int score;

    private String desc;

    public Musica(String nome, String imagem, String desc) {
        this.nome = nome;
        this.imagem = imagem;
        this.desc = desc;
        this.score = 0;
    }

    public String getNome(){
        return this.nome;
    }

    public String getImagem(){
        return this.imagem;
    }

    public String getDesc(){
        return this.desc;
    }

    public int getScore(){
        return this.score;
    }
    public void aumentar_pontuacao(){

        score = score +1;
    }



}
