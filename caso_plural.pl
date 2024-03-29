% Aluno: Pedro Paulo Freire Oliveira
% Disciplina: Inteligencia Artificial 2017.2
% Professor: Herman Martin Gomes
%
%
% C�digo prolog que analisa quais palavras pertencem a casos proibitivos
% para suceder a crase do caso plural (�s) com base na estutura��o da
% palavra, reconhcida atrav�s da identifica��o de termina��es, conforme
% o l�xico da lingua portugesa.
%

% Rela��o auxiliar para identificar o caracter que finaliza a palavra.
%
last_char(S, X) :-
    name(S, N),
    reverse(N, [F|_]),
    name(X, [F]).

% Rela��o auxiliar para identificar os 2 ultimos caracteres que
% finalizam a palavra.
%
terminacao(Palavra, T) :-  string_length(Palavra, L), L > 1, substring(Palavra, L-1, 2, T).

% Terminacoes proibitivas: casos gerais em que as palavras que terminam
% nas respectivas letras s�o casos proibitivos, levadas em considera��o
% tamb�m as exce��es.
%
%
proibida(Palavra) :-  \+ last_char(Palavra, s), \+ excecao(Palavra).

proibida(Palavra) :-  terminacao(Palavra, �s), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, es), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, �s), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, �s), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, is), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, ns), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, os), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, �s), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, �s), \+ excecao(Palavra).
proibida(Palavra) :-  terminacao(Palavra, us), \+ excecao(Palavra).
proibida(bit).
proibida(agoras).
proibida(foras).



% Excecoes gerais
excecao(p�s).
excecao(r�s).
excecao(leis).
excecao(xerox).
excecao(x�rox).
excecao(turn�s).
excecao(motos).
excecao(fotos).
excecao(internets).

% Exce��es em -m
excecao(nuvens).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, agens), Palavra \== pagens.


% Exce��es: feminino singular terminadas em -�o.
excecao(m�os).
excecao(raz�es).
excecao(rebeli�es).
excecao(li��es).
excecao(tens�es).
excecao(vis�es).
excecao(quest�es).
excecao(Palavra) :-  string_length(Palavra, L), L > 6, substring(Palavra, L-5, 6, n��os).
excecao(Palavra) :-  string_length(Palavra, L), L > 6, substring(Palavra, L-5, 6, n��es).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, a��es), Palavra \== cora��es.
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, ss�o).
excecao(Palavra) :-  string_length(Palavra, L), L > 6, substring(Palavra, L-5, 6, di��es).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, us�es).
excecao(Palavra) :-  string_length(Palavra, L), L > 6, substring(Palavra, L-5, 6, uls�es).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, ns�es).
excecao(Palavra) :-  string_length(Palavra, L), L > 7, substring(Palavra, L-6, 7, vers�es).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, e��es), Palavra \== cabe��es, Palavra \== trope��es.


% Exce��es: feminino singular terminadas em -r
excecao(dores).
excecao(cores).
excecao(mulheres).
excecao(colheres).
excecao(chanceleres).
excecao(f�mures).


% Exce��es: feminino singular terminadas em -l
excecao(tais).
excecao(vogais).
excecao(estatais).
excecao(federais).
excecao(cais).
excecao(cales).


% Exce��es: feminino singular terminadas em -z
excecao(vozes).
excecao(vezes).
excecao(pazes).
excecao(nozes).
excecao(luzes).
excecao(fozes).
excecao(ra�zes).
excecao(cruzes).
excecao(nudezas).
excecao(mudezas).
excecao(matizes).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, idezes).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, trizes).


% Exce��es: feminino singular terminadas em -e
excecao(m�es).
excecao(lajes).
excecao(sedes).
excecao(fomes).
excecao(tosses).
excecao(peles).
excecao(fases).
excecao(teses).
excecao(fezes).
excecao(acnes).
excecao(sortes).
excecao(proles).
excecao(pebles).
excecao(partes).
excecao(mentes).
excecao(lentes).
excecao(fontes).
excecao(hastes).
excecao(frases).
excecao(foices).
excecao(noites).
excecao(febres).
excecao(estirpes).
excecao(couves).
excecao(alfaces).
excecao(chaves).
excecao(�stases).
excecao(�rvores).
excecao(n�mades).
excecao(metades).
excecao(g�neses).
excecao(queloides).
excecao(tietes).
excecao(raquetes).
excecao(faces).
excecao(bases).
excecao(crases).
excecao(naves).
excecao(raves).
excecao(paredes).
excecao(redes).
excecao(sementes).
excecao(tardes).
excecao(noites).
excecao(classes).
excecao(doente).
excecao(crentes).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, netes).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, dades).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, antes).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, entes).
excecao(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, istas).


% Novas rela��es obtidas por aprendizado abaixo:
%
