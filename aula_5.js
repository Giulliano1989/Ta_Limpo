const GLOBAL_URL = "https://6a50327cf45d5352b6121ab2.mockapi.io/produtos";

async function carregarProdutos() {
	try {
		const response = await fetch(GLOBAL_URL);
		const produtos = await response.json();

		if (!response.ok) {
			throw new Error("Erro na requisição");
		}

		popularTabela(produtos);
	} catch (error) {
		console.error(error);
		alert("Não foi possível carregar os produtos");
	}
}

function popularTabela(produtos) {
	const tbody = document.querySelector("#table_produtos tbody");
	tbody.innerHTML = `
		<tr>
			<td colspan="6" class="py-3">
				<div class="d-flex align-items-center">
					<span class="spinner-border text-primary me-2" role="status"></span>
					<span>Carregando...</span>
				</div>
			</td>
		</tr>
	`;

	let html = "";

	for (const produto of produtos) {
		html += `
			<tr>
				<td>${produto.id}</td>
				<td>${produto.nome}</td>
				<td>${formatarMoeda(Number(produto.preco))}</td>
				<td>${produto.quantidade}</td>
				<td>${formatarMoeda(Number(produto.preco) * produto.quantidade)}</td>
				<td>
				<button class="btn btn-danger" onclick="remover(${produto.id})">
					Remover
				</button>
				</td>
			</tr>
		`;
	}

	tbody.innerHTML = html;
}

async function remover(id) {
	try {
		const url = `${GLOBAL_URL}/${id}`;
		await fetch(url, {
			method: "DELETE",
		});
		carregarProdutos();
	} catch (error) {
		console.error(error);
		alert("Erro ao deletar o produto");
	}
}

function formatarMoeda(valor) {
	return valor.toLocaleString("pt-BR", {
		style: "currency",
		currency: "BRL",
	});
}

carregarProdutos();
