package Servlet;

import Classes.Cardapio;
import Classes.Cliente;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GerarXML extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Object pedidoCliente = request.getSession().getAttribute("pedidoCliente");
            String nomeCliente = request.getParameter("nomeCliente");
            String celularCliente = request.getParameter("celularCliente");
            String enderecoCliente = request.getParameter("enderecoCliente");

            ArrayList<Cliente> dadosCliente = new ArrayList<Cliente>();
            ArrayList<Cardapio> dadosPedido = new ArrayList<Cardapio>();
            if (pedidoCliente != null) {
                ArrayList<Cardapio> pedido = (ArrayList<Cardapio>) pedidoCliente;

                for (int i = 0; i < pedido.size(); i++) {

                    Cliente cliente = new Cliente();
                    String nome = pedido.get(i).getNome();
                    int quantidade = pedido.get(i).getQuantidade();
                    String descricao = pedido.get(i).getDescricao();

                    Double preco = pedido.get(i).getPreco();
                    int calorias = pedido.get(i).getCalorias();
                    cliente.setNome(nomeCliente);
                    cliente.setCelular(celularCliente);
                    cliente.setEndereço(enderecoCliente);
                    cliente.setDataPedido(new Date(System.currentTimeMillis()));
                    Cardapio nomeQuantidadePedido = new Cardapio(nome, preco, descricao, calorias, quantidade);
                    dadosPedido.add(nomeQuantidadePedido);
                    dadosCliente.add(cliente);
                }
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.newDocument();
            Element rootTag = doc.createElement("pedido");

            Cliente c = dadosCliente.get(0);
            Element dataHora = doc.createElement("criado");
            Element clienteNome = doc.createElement("nome");
            Element clienteCelular = doc.createElement("numero_telefone");
            Element clienteEndereco = doc.createElement("endereco");
            dataHora.setTextContent(String.valueOf(c.getDataPedido()));
            clienteNome.setTextContent(String.valueOf(c.getNome()));
            clienteCelular.setTextContent(String.valueOf(c.getCelular()));
            clienteEndereco.setTextContent(String.valueOf(c.getEndereço()));
            Element itens = doc.createElement("itens");

            for (Cardapio cardapio : dadosPedido) {
                if (cardapio.getQuantidade() != 0) {
                    Element item = doc.createElement("item");
                    itens.appendChild(item);

                    Element nomeProduto = doc.createElement("nome");
                    item.appendChild(nomeProduto);
                    nomeProduto.setTextContent(String.valueOf(cardapio.getNome()));

                    Element quantidade = doc.createElement("quantidade");
                    item.appendChild(quantidade);
                    quantidade.setTextContent(String.valueOf(cardapio.getQuantidade()));

                    itens.appendChild(item);
                    item.appendChild(nomeProduto);
                    item.appendChild(quantidade);
                }
            }
            rootTag.appendChild(dataHora);
            rootTag.appendChild(clienteNome);
            rootTag.appendChild(clienteCelular);
            rootTag.appendChild(clienteEndereco);
            rootTag.appendChild(itens);

            doc.appendChild(rootTag);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            DOMSource domSource = new DOMSource(doc);
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);
            System.out.println(sw.toString());

            File f = new File("C:\\SENAC\\XML\\pedido.xml");
            FileOutputStream fout = new FileOutputStream(f);
            DataOutputStream dout = new DataOutputStream(fout);
            dout.write(sw.toString().getBytes());
            dout.close();
            fout.close();

            out.println("<h1>Pedido Gerado com Sucesso!</h1>");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GerarXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GerarXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GerarXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(GerarXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
