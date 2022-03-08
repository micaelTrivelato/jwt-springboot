# jwt 

Para testar o projeto, você precisa da ferramente POSTMAN.
primeiramente você deve chamar a API http://localhost:8080/login passando o seguinte body em formato JSON:

Para usuário Admin:
<pre>
  {<br>
      <strong>"login"</strong>:"admin@admin.com", <br>
      <strong>"password"</strong>:"3J.5.Q#fZ$9&*O"<br>
  }
</pre>
Para Usuário Gerente:
<pre>
  {<br>
      <strong>"login"</strong>:"gerente@gerente.com",<br>
      <strong>"password"</strong>:"3J.5.Q#fZ$9&*O"<br>
  }</pre>
  
  Com isso você terá acesso às demais APIS do projeto através do token gerado, toda API necessita do BEARER Token.
 <pre> 
  Permissões:
    Admin: tem acesso às API's de Permissões e Users;
    Gerente: Tem acesso às API's de Users.
  
  Banco de Dados:
    Banco de memória H2, não é necessário se preocupar com senha e usuario, apenas execute o projeto
</pre>
