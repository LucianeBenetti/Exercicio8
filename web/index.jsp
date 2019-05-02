<%-- 
    Document   : index
    Created on : 17/04/2019, 08:15:57
    Author     : 80130917
--%>
<%@page import="java.lang.Double"%>
<%@page import="Classes.Cardapio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Servlet.XML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css">
        <title>Cardapio</title>
    </head>
    <body>

        <header class="buscarMenu">
            <h4>Clique no botão abaixo e acesse nosso cardápio especial de tortas e sobremesas: </h4>
            <form action="xml" method="POST"> 
                <input type="submit" name="conhecerCardapio" value="Conhecer Cardápio">
            </form>
        </header>       
<form name="pedido" action="pedido" method="POST">
        <%
            Object cardapio = request.getAttribute("conteudoCardapio");
            if (cardapio != null) {
                ArrayList<Cardapio> menu = (ArrayList<Cardapio>) cardapio;
        %>
        <section class="secao1">
            <h3>Conheça nosso Menu de Tortas e Sobremesas</h3>   
                <table border="0">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Descricao</th>
                            <th>Calorias</th>
                            <th>Preço (R$) </th>
                            <th>Quantidade</th>
                        </tr>
                    </thead>
                    <%
                        for (int i = 0; i < menu.size(); i++) {
                            Cardapio itemDeCardapio = menu.get(i);
                    %>
                    <tr>
                        <td class="dadosPedido"><% out.print(itemDeCardapio.getNome()); %></td>
                        <td ><% out.print(itemDeCardapio.getDescricao()); %></td>
                        <td ><% out.print(itemDeCardapio.getCalorias()); %></td>
                        <td ><% out.print(itemDeCardapio.getPreco());%></td>
                        <td class="quantidade"> <input type="number" style="width: 50px" min="0" value="0" name="valores_<%= i%>"></td>
                    </tr>
                    <%   }  %>
                    <br />          
                </table>
                <br>
                <br>
                <input type="submit" name="enviar" value="Enviar Pedido">
            </form>
        </section>
        <%}%>
    </body>
</html>
