package LEXICO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class analiseLexica {

    public static void ComparadorRegex(boolean argumento, ArrayList<String> argumentos, ArrayList<String> palavraReservada, ArrayList<String> arq){
        
        boolean arg = argumento;
        ArrayList<String> listaArgumentos = argumentos;
        
        ArrayList<String[]> listaPalavras = new ArrayList<>();
        ArrayList<tokens> token = new ArrayList<>();
        ArrayList<erro> erros = new ArrayList<>();
        
        LinkedList<String> copiaTokens = new LinkedList();

        boolean modoString = false;
        String texto = "";
        
        System.out.println("\nIniciando analize lexica...\n");
        
        for(int x = 0; x < arq.size() - 1; x++){
            try {
                listaPalavras.add(arq.get(x).split(" "));
            }catch (Exception e){
                System.out.println("ERRO: Problema na divisao do arquivo\n" + e);
            }
        }

        for(int y = 0; y < listaPalavras.size(); y++){
            for(int z = 0; z < listaPalavras.get(y).length; z++){

                Pattern pattern2 = Pattern.compile("[\"]");
                Matcher matcher2 = pattern2.matcher(listaPalavras.get(y)[z]);
                if(matcher2.find()){
                    modoString = true;
                    while (modoString){
                        if (!listaPalavras.get(y)[z].endsWith("\"")){
                            texto += " " + listaPalavras.get(y)[z];
                        }else {
                            texto += " "+ listaPalavras.get(y)[z];
                            modoString = false;
                        }
                        z++;
                    }
                    token.add(new tokens("TK_string", texto, y, z));
                    texto = "";
                }

                if(listaPalavras.get(y)[z].matches("-[0-9]+")){
                    token.add(new tokens("TK_numneg", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("[0-9]+")){
                    token.add(new tokens("TK_numpos", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches(";")){
                    token.add(new tokens("TK_finallinha", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("\\(")){
                    token.add(new tokens("TK_abreparent", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("\\)")){
                    token.add(new tokens("TK_fechaparent", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("\\{")){
                    token.add(new tokens("TK_iniciobloco", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("}")){
                    token.add(new tokens("TK_fimbloco", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("=")){
                    token.add(new tokens("TK_igual", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("==")){
                    token.add(new tokens("TK_igualigual", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches(">")){
                    token.add(new tokens("TK_maior", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("<")){
                    token.add(new tokens("TK_menor", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("<>")){
                    token.add(new tokens("TK_diferente", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("\\+\\+]")){
                    token.add(new tokens("TK_incremento", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("--")){
                    token.add(new tokens("TK_decremento", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("\\+")){
                    token.add(new tokens("TK_soma", listaPalavras.get(y)[z], y, z));
                }
                else if (listaPalavras.get(y)[z].matches("\\*")){
                    token.add(new tokens("TK_mult", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("/")){
                    token.add(new tokens("TK_div", listaPalavras.get(y)[z], y, z));
                }

                else if (listaPalavras.get(y)[z].matches("-")){
                    token.add(new tokens("TK_sub", listaPalavras.get(y)[z], y, z));
                }

                else if (palavraReservada.contains(listaPalavras.get(y)[z])){
                    Pattern pattern = Pattern.compile("[a-z]");
                    Matcher matcher = pattern.matcher(listaPalavras.get(y)[z]);
                    if(matcher.find()){
                        if(listaPalavras.get(y)[z].matches("inicio")){
                            token.add(new tokens("TK_inicio", listaPalavras.get(y)[z], y, z));
                        }

                        if(listaPalavras.get(y)[z].matches("fim")){
                            token.add(new tokens("TK_fim", listaPalavras.get(y)[z], y, z));
                        }

                        if(listaPalavras.get(y)[z].matches("inteiro")){
                            token.add(new tokens("TK_inteiro", listaPalavras.get(y)[z], y, z));
                        }

                        if(listaPalavras.get(y)[z].matches("leia")){
                            token.add(new tokens("TK_leia", listaPalavras.get(y)[z], y, z));
                        }

                        if(listaPalavras.get(y)[z].matches("escreva")){
                            token.add(new tokens("TK_escreva", listaPalavras.get(y)[z], y, z));
                        }

                        if(listaPalavras.get(y)[z].matches("enquanto")){
                            token.add(new tokens("TK_enquanto", listaPalavras.get(y)[z], y, z));
                        }

                        if(listaPalavras.get(y)[z].matches("se")){
                            token.add(new tokens("TK_se", listaPalavras.get(y)[z], y, z));
                        }

                        if(listaPalavras.get(y)[z].matches("senao")){
                            token.add(new tokens("TK_senao", listaPalavras.get(y)[z], y, z));
                        }
                    }
                }
                
                else if (listaPalavras.get(y)[z].matches("[a-zA-Z]")){
                    token.add(new tokens("TK_variavel", listaPalavras.get(y)[z], y, z));
                }

                else {
                    erros.add(new erro(listaPalavras.get(y)[z], y, z));
                }
            }
        }
        
        if(erros.size() > 0){
            System.out.println("\nERRO LEXICO\n");
            for(erro e : erros){
                System.out.println("Caractere(s): "+e.getNome()+" não identificado"+" -> "+"Linha: "+e.getLinha()+" -> "+"Coluna: "+e.getColuna()+"\n");
            }
            return;
        }
        
        if(argumento == false){
            System.out.println("Analize lexica concluida com sucesso, nenhum erro identificado, total de tokens: "+token.size()+"\n");
        }

        if(argumento == true && (argumentos.contains("-lt") | argumentos.contains("-tudo"))){
            for(tokens t : token){
                System.out.println("Token: "+t.getNome()+" -> "+"Lexema: "+t.getLexemas()+" -> "+"Linha: "+t.getLinha()+" -> "+"Coluna: "+t.getColuna());
            }
            System.out.println("\nAnalize lexica concluida com sucesso!"+"\n");
        }
        
        Collections.reverse(token);
        for(tokens t : token){
            copiaTokens.add(t.getNome());
        }
        copiaTokens.add("$");
        Collections.reverse(token);
        
        SINTATICO.analiseSintatica.analisadorSintatico(arg, listaArgumentos, copiaTokens);
    }
}