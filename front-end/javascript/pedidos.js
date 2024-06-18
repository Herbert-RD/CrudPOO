function openModal() {
    document.getElementById('pedidoModal').style.display = "block";
}

function closeModal() {
    document.getElementById('pedidoModal').style.display = "none";
}

// -------------------- LISTAR PEDIDOS ---------------------------------------

document.addEventListener("DOMContentLoaded", function () {
    // URL da API de pedidos
    const url = "http://localhost:8080/vendas";

    // Função para criar o HTML do card de pedido
    function criarCardPedido(pedido) {
        const div = document.createElement("div");
        div.className = "pedido pendente";

        const nome = document.createElement("p");
        nome.textContent = `Nome Cliente: ${pedido.nomeCliente}`;
        div.appendChild(nome);

        const item = document.createElement("p");
        fetch(`http://localhost:8080/produtos/${pedido.produtoId}`)
            .then(response => response.json())
            .then(produto => {
                item.textContent = `Nome do produto: ${produto.nome}`;
                div.appendChild(item);
            })

        const payment = document.createElement("p");
        payment.textContent = `Tipo de pagamento: ${pedido.tipoPagamento}`;
        div.appendChild(payment);

        const id = document.createElement("p");
        id.textContent = `ID: ${pedido.id}`;
        div.appendChild(id);

        return div;
    }

    // Função para buscar os pedidos e exibir no HTML
    function buscarPedidos() {
        fetch(url)
            .then(response => response.json())
            .then(pedidos => {
                const container = document.getElementById("pedidosWrapper");
                container.innerHTML = ""; // Limpa o container antes de adicionar os novos elementos
                pedidos.forEach(pedido => {
                    const card = criarCardPedido(pedido);
                    container.appendChild(card);
                });
            })
            .catch(error => {
                console.error("Erro ao buscar pedidos:", error);
            });
    }

    // Buscar os pedidos quando a página for carregada
    buscarPedidos();
});









// --------------------- ADICIONAR PEDIDO -----------------------------

document.getElementById('productForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const produtoId = document.getElementById('produtoId').value;
    const nomeCliente = document.getElementById('nomeCliente').value;
    const tipoPagamento = document.getElementById('tipoPagamento').value;

    const data = {
        produtoId: parseInt(produtoId),
        nomeCliente: nomeCliente,
        tipoPagamento: tipoPagamento
    };

    fetch('http://localhost:8080/vendas', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            alert('Produto registrado com sucesso!');
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Erro ao registrar o produto.');
        });
});
