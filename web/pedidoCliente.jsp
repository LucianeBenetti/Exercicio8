<%-- 
    Document   : Pedido
    Created on : 24/04/2019, 08:34:11
    Author     : 80130917
--%>

<%@page import="java.lang.Integer"%>
<%@page import="Classes.Cardapio"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="estilo.css">
        <title>Cadastro de Pedidos</title>
    </head>
    <body>
        <%
            Integer caloriasTotal = ((Integer) (request.getAttribute("calorias"))).intValue();

            Object pedidoCliente = request.getAttribute("pedidoCliente");
            Object valorTotal = request.getAttribute("valor");

            if (pedidoCliente != null) {
                ArrayList<Cardapio> pedido = (ArrayList<Cardapio>) pedidoCliente;
                
        %>   
        <section class="secao2">
            <h3>Pedido Efetuado</h3>   

            <table border="0">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Quantidade</th>
                        <th>Preço (R$) </th>
                        <th>Calorias</th>

                    </tr>
                </thead>

                <%      for (int i = 0; i < pedido.size(); i++) {
                        Cardapio opcoesCardapio = pedido.get(i);
                %>
                <tr>
                    <td class="dadosPedido"><% out.print(opcoesCardapio.getNome()); %></td>
                    <td><% out.print(opcoesCardapio.getQuantidade()); %></td>
                    <td><% out.print(opcoesCardapio.getPreco());%></td>
                    <td><% out.print(opcoesCardapio.getCalorias()); %></td>
                </tr>
                <%}%>
                <br />  

                <tr>
                    <td class="total" colspan="2">Total</td>
                    <td>R$ <%= valorTotal%></td>
                    <td><%= caloriasTotal%></td>
                </tr>

            </table>
            <%}%>
        </section>

        <section class="secao3">
            <form action="gerarxml" method="POST">
                <h3>Digite seus dados para processar o pedido</h3>

                Nome do cliente:
                <input type="text" name="nomeCliente" required><br><br>
                Endereço:
                <input type="text" name="enderecoCliente" required><br><br>
                Celular:
                <input type="text" name="celularCliente" required><br><br>
                <br>
                <br>
                <input type="submit" name="enviar" value="Efetuar Pedido">
            </form>
        </section>
    </body>
</html>
