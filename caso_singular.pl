% Aluno: Pedro Paulo Freire Oliveira
% Disciplina: Inteligencia Artificial 2017.2
% Professor: Herman Martin Gomes
%
%
% Código prolog que analisa quais palavras pertencem a casos proibitivos
% para suceder a crase do caso singular (à) com base na estuturação da
% palavra, reconhcida através da identificação de terminações, conforme
% o léxico da lingua portugesa.
%

% Relação auxiliar para identificar o caracter que finaliza a palavra.
%
last_char(S, X) :-
    name(S, N),
    reverse(N, [F|_]),
    name(X, [F]).

% Terminacoes proibitivas: casos gerais em que as palavras que terminam
% nas respectivas letras são casos proibitivos, levadas em consideração
% também as exceções.
%
proibida(Palavra) :-  last_char(Palavra, á), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, e), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, é), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, ê), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, i), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, l), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, m), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, n), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, o), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, ó), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, ô), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, r), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, s), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, u), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, x), \+ excecao(Palavra).
proibida(Palavra) :-  last_char(Palavra, z), \+ excecao(Palavra).
proibida(bit).
proibida(agora).
proibida(fora).


% Excecoes gerais
excecao(ré).
excecao(pá).
excecao(lei).
excecao(vis).
excecao(xérox).
excecao(xerox).
excecao(xérox).
excecao(turnê).
excecao(moto).
excecao(foto).
excecao(internet).


% Exceções em -m
excecao(nuvem).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, agem), Palavra \== pagem.
excecao(jovem).


% Exceções: feminino singular terminadas em -ão.
excecao(mão).
excecao(razão).
excecao(rebelião).
excecao(lição).
excecao(tensão).
excecao(visão).
excecao(questão).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, nção).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, ação), Palavra \== coração.
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, ssão).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, dição).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, usão).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, ulsão).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, nsão).
excecao(Palavra) :-  string_length(Palavra, L), L > 6, substring(Palavra, L-5, 6, versão).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, eção), Palavra \== cabeção, Palavra \== tropeção.


% Exceções: feminino singular terminadas em -r
excecao(dor).
excecao(cor).
excecao(mulher).
excecao(colher).
excecao(chanceler).
excecao(fêmur).


% Exceções: feminino singular terminadas em -l
excecao(tal).
excecao(vogal).
excecao(estatal).
excecao(federal).
excecao(cal).
excecao(policial).

% Exceções: feminino singular terminadas em -z
excecao(voz).
excecao(vez).
excecao(paz).
excecao(noz).
excecao(luz).
excecao(foz).
excecao(raíz).
excecao(cruz).
excecao(nudez).
excecao(mudez).
excecao(matiz).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, idez).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, triz).


% Exceções: feminino singular terminadas em -e
excecao(mãe).
excecao(laje).
excecao(sede).
excecao(fome).
excecao(tosse).
excecao(pele).
excecao(fase).
excecao(tese).
excecao(feze).
excecao(acne).
excecao(sorte).
excecao(prole).
excecao(peble).
excecao(parte).
excecao(mente).
excecao(lente).
excecao(fonte).
excecao(noite).
excecao(tarde).
excecao(haste).
excecao(frase).
excecao(foice).
excecao(febre).
excecao(estirpe).
excecao(couve).
excecao(alface).
excecao(chave).
excecao(êstase).
excecao(face).
excecao(base).
excecao(crase).
excecao(nave).
excecao(rave).
excecao(parede).
excecao(rede).
excecao(semente).
excecao(árvore).
excecao(nômade).
excecao(metade).
excecao(gênese).
excecao(queloide).
excecao(tiete).
excecao(raquete).
excecao(classe).
excecao(doente).
excecao(crentes).
excecao(chefe).
excecao(interprete).
excecao(herege).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, nete).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, dade).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, ante).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, ente).
excecao(Palavra) :-  string_length(Palavra, L), L > 4, substring(Palavra, L-3, 4, ista).

% Novas relações obtidas por aprendizado abaixo:
%
excecao(vontade).

