const GLOBAL_URL = "https://6a56d0efb17de7bebbde7e05.mockapi.io/veiculos";

async function carregarVeiculos() {
    try {
        const response = await fetch(GLOBAL_URL);
        const veiculos = await response.json();

        if (!response.ok) {
            throw new Error("Erro na requisição");
        }

        popularTabela(veiculos);
    } catch (error) {
        console.error(error);
        alert("Não foi possível carregar os veículos");
    }
}

function popularTabela(veiculos) {
    let html = "";

    for (const veiculo of veiculos) {
        html += `
            <tr>
                <td>${veiculo.id}</td>
                <td>${veiculo.marca}</td>
                <td>${veiculo.modelo}</td>
                <td>${veiculo.placa}</td>
                <td><button class="btn btn-primary" type="submit">Iniciar</button></td>
            </tr>
        `;
    }

    const tbody = document.querySelector("#table_veiculos tbody");

    if (tbody) {
        tbody.innerHTML = html;
    }
}

carregarVeiculos();
