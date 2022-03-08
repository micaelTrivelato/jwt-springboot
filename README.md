# jwt 

Para testar o projeto, você precisa da ferramente POSTMAN.
primeiramente você deve chamar a API http://localhost:8080/login passando o seguinte body em formato JSON:

Para usuário Admin:

  {
      "login":"admin@admin.com",
      "password":"3J.5.Q#fZ$9&*O"
  }

Para Usuário Gerente:

  {
      "login":"gerente@gerente.com",
      "password":"3J.5.Q#fZ$9&*O"
  }
  
  Com isso você terá acesso às demais APIS do projeto através do token gerado, toda API necessita do BEARER Token.
  
  Permissões:
    Admin: tem acesso às API's de Permissões e Users;
    Gerente: Tem acesso às API's de Users.
  
  Banco de Dados:
    Banco de memória H2, não é necessário se preocupar com senha e usuario, apenas execute o projeto
