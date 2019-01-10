Instruções para o uso do Leitor de QRCode:

1- Entre na pasta sql, e rode os scripts no MySQL.
2- Abra a pasta do projeto no Eclipse (ou outra IDE) e altere a classe de acesso ao banco com seu usuario e senha (classe PIFactory no package dao).
3- Execute a classe Tela do package tela.
4- O leitor reconhece três tipos de documentos: CNH, CTPS e E-Titulo. Eles estão armazenados em qrcodes no formato de imagem na pasta documents na raíz do projeto. Clique em ler Arquivo para escolher um dos QRCodes da pasta. As imagens com sufixo 2 são documentos válidos, enquanto os outros são documentos inválidos.
5- Caso queira ler pela webcam, passe as imagens dos qrcodes para um celular ou imprima. Na janela principal da tela do programa, clique em Webcam e aponte o QRCode para a webcam. 
6- Após a leitura do qrcode, o web service irá enviar uma requisição para o servidor que irá devolver os dados da pessoa do documento caso o qrcode do documento seja válido. Caso seja inválido, uma mensagem de inválido será retornada.

###############################################################################

Como funciona o programa:

O conteúdo das imagens dos qrcodes contem uma criptografia que foi gerada pelo web service. Essa criptografia contém os dados de algum usuário cadastrado no banco.
Quando o programa lê algum qrcode, ele pega o conteúdo do mesmo e o envia por um JSON e recebe de volta os dados do usuário descriptografados. Esses dados serão comparados com os documentos cadastrados no banco e caso seja válido, será printado na tela as informações do dono do documento.
No banco de dados, há um script de um log, onde ficam registrados as leituras válidas ou inválidas, a data e hora e outras informações.
