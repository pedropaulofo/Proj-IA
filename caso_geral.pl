% Aluno: Pedro Paulo Freire Oliveira
% Disciplina: Inteligencia Artificial 2017.2
% Professor: Herman Martin Gomes
%
%
% Código prolog que analisa quais palavras pertencem a casos proibitivos
% para suceder a crase do caso singular (à) ou plural (às) com base na
% léxica da palavra.


% ARTIGOS
%
p(a).
p(uma).
p(as).
p(umas).

% PRONOMES
%
p(ela).
p(elas).

p(esta).
p(estas).
p(essa).
p(essas).
p(aquela).
p(aquelas).

p(alguma).
p(algumas).
p(nenhuma).
p(nenhumas).
p(muita).
p(muitas).
p(pouca).
p(poucas).
p(toda).
p(todas).
p(outra).
p(outras).
p(certa).
p(certas).
p(quanta).
p(quantas).
p(cada).
p(nada).

p(cuja).
p(cujas).

% ADVERBIOS
%
p(nunca).
p(ainda).
p(apenas).
p(afora).
p(acima).
p(depressa).
p(ora).
p(Palavra) :-  string_length(Palavra, L), L > 5, substring(Palavra, L-4, 5, mente), Palavra \== semente.




















