


// ------------------------------ GET ALL PRODUCTS ------------------------------------

document.addEventListener('DOMContentLoaded', () => {
    const produtosContainer = document.getElementById('catalogoWrapper');

    // Função para criar o card do produto
    function criarCardProduto(produto) {
        const card = document.createElement('div');
        card.classList.add('pedido', 'estoque');

        const nome = document.createElement('p');
        nome.textContent = `Nome: ${produto.nome}`;

        const preco = document.createElement('p');
        preco.textContent = `Preço: R$${produto.preco.toFixed(2)}`;

        const id = document.createElement('p');
        id.textContent = `ID: ${produto.id}`;

        card.appendChild(nome);
        card.appendChild(preco);
        card.appendChild(id);

        return card;
    }

    // Função para buscar produtos na API
    function fetchProdutos() {
        fetch('http://localhost:8080/produtos')
            .then(response => response.json())
            .then(produtos => {
                produtos.forEach(produto => {
                    const card = criarCardProduto(produto);
                    produtosContainer.appendChild(card);
                });
            })
            .catch(error => {
                console.error('Erro ao buscar produtos:', error);
            });
    }

    // Chama a função para buscar produtos quando a página carregar
    fetchProdutos();
});


// ---------------------- REGISTER PRODUCT ----------------------------
document.getElementById('productForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const cor = document.getElementById('cor').value;
    const tamanho = document.getElementById('tamanho').value;
    const preco = document.getElementById('preco').value;

    const product = {
        nome: nome,
        cor: cor,
        tamanho: tamanho,
        preco: parseFloat(preco)
    };

    fetch('http://localhost:8080/produtos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('message').innerText = 'Produto registrado com sucesso!';
        })
        .catch(error => {
            document.getElementById('message').innerText = 'Erro ao registrar o produto.';
        });
});