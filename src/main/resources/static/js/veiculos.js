const GLOBAL_URL = "https://6a56d0efb17de7bebbde7e05.mockapi.io/veiculos";

async function carregarVeiculos() {
  try {
    const response = await fetch(GLOBAL_URL);
    if (!response.ok) {
      throw new Error("Erro na requisição");
    }

    const veiculos = await response.json();
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
                <td>${escaparHtml(veiculo.id)}</td>
                <td>${escaparHtml(veiculo.marca)}</td>
                <td>${escaparHtml(veiculo.modelo)}</td>
                <td>${escaparHtml(veiculo.placa)}</td>
                <td><button class="btn btn-primary" type="button" onclick="atualizarStatus(${veiculo.id}, 'lavando')">Iniciar</button>
                <button class="btn btn-danger" type="button" onclick="excluirVeiculo(${veiculo.id})">Excluir</button></td>
                
            </tr>
        `;
  }

  const tbody = document.querySelector("#table_veiculos tbody");

  if (tbody) {
    tbody.innerHTML = html;
  }
}

async function excluirVeiculo(id) {
  if (!confirm("Tem certeza que deseja excluir este veículo?")) {
    return;
  }

  const url = `${GLOBAL_URL}/${id}`;

  try {
    const response = await fetch(url, { method: "DELETE" });
    if (!response.ok) {
      throw new Error("Erro na exclusão");
    }
  } catch (error) {
    console.error(error);
    alert("Não foi possível excluir o veículo");
  } finally {
    carregarVeiculos();
  }
}

async function cadastrarVeiculo() {
  const form = document.querySelector("#form-cadastro-veiculo");
  if (!form || !form.checkValidity()) {
    form?.reportValidity();
    return;
  }

  const veiculo = {
    marca: document.querySelector("#marca").value,
    modelo: document.querySelector("#modelo").value,
    placa: document.querySelector("#placa").value,
    status: "aguardando",
  };
  try {
    const response = await fetch(GLOBAL_URL, {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify(veiculo),
    });
    if (!response.ok) {
      throw new Error("Erro no cadastro");
    }
    form.reset();
    fecharModal();
    carregarVeiculos();
  } catch (error) {
    console.error(error);
    alert("Não foi possível cadastrar o veículo");
  }
}

async function atualizarStatus(id, status) {
  try {
    const response = await fetch(`${GLOBAL_URL}/${id}`, {
      method: "PUT",
      headers: { "content-type": "application/json" },
      body: JSON.stringify({ status }),
    });
    if (!response.ok) {
      throw new Error("Erro na atualização do status");
    }
    carregarVeiculos();
  } catch (error) {
    console.error(error);
    alert("Não foi possível atualizar o status");
  }
}

function escaparHtml(valor) {
  const elemento = document.createElement("span");
  elemento.textContent = valor ?? "";
  return elemento.innerHTML;
}

function fecharModal() {
  const modalHtml = document.querySelector("#modal_veiculos");
  if (modalHtml) {
    const modal = bootstrap.Modal.getOrCreateInstance(modalHtml);
    modal.hide();
  }
}
carregarVeiculos();
