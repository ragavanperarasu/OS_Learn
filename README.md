%{
#include <stdio.h>
#include <stdlib.h>

void yyerror(const char *s);
int yylex();
%}

%token ID NUM ASSIGN PLUS SEMI

%%
program:
        stmt            { printf("Valid syntax\n"); }
        ;

stmt:
        ID ASSIGN expr SEMI
        ;

expr:
        ID
      | NUM
      | expr PLUS expr
      ;
%%

void yyerror(const char *s) {
    printf("Syntax error\n");
}