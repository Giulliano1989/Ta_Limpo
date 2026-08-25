const GLOBAL_URL = "https://6a56d0efb17de7bebbde7e05.mockapi.io/veiculos";

async function carregarVeiculosAguardando() {
  const response = await fetch(`${GLOBAL_URL}?status=aguardando`);
  const data = await response.json();

  listarVeiculos(
    data,
    document.querySelector("#lista_aguardando"),
    "Iniciar lavagem",
    "lavando",
  );
}

async function carregarVeiculosLavando() {
  const response = await fetch(`${GLOBAL_URL}?status=lavando`);
  const data = await response.json();

  listarVeiculos(
    data,
    document.querySelector("#lista_lavando"),
    "Finalizar lavagem",
    "finalizado",
  );
}

async function carregarVeiculosFinalizado() {
  const response = await fetch(`${GLOBAL_URL}?status=finalizado`);
  const data = await response.json();

  listarVeiculos(data, document.querySelector("#lista_finalizado"), "", null);
}

function listarVeiculos(veiculos, lista, textoBotao, novoStatus) {
  let html = "";

  for (const veiculo of veiculos) {
    html += `
      <div class="card mb-3">
        <div class="card-body">
          <div class="d-flex align-items-center mb-3">
            <i class="bi bi-car-front fs-3 text-primary me-3"></i>

            <div>
              <h6>${veiculo.marca} ${veiculo.modelo} - ${veiculo.placa}</h6>
            </div>
          </div>

          ${
            novoStatus
              ? `<button
                   type="button"
                   class="btn btn-primary w-100"
                   onclick="atualizarStatus(${veiculo.id}, '${novoStatus}')">
                   ${textoBotao}
                 </button>`
              : ""
          }
        </div>
      </div>
    `;
  }

  lista.innerHTML = html;
}

async function atualizarStatus(id, status) {
  try {
    const respostaVeiculo = await fetch(`${GLOBAL_URL}/${id}`);

    if (!respostaVeiculo.ok) {
      throw new Error("Veículo não encontrado");
    }

    const veiculo = await respostaVeiculo.json();

    const response = await fetch(`${GLOBAL_URL}/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        marca: veiculo.marca,
        modelo: veiculo.modelo,
        placa: veiculo.placa,
        status: status,
      }),
    });

    if (!response.ok) {
      throw new Error(`Erro ao atualizar: ${response.status}`);
    }

    await init();
  } catch (error) {
    console.error(error);
    alert(error.message);
  }
}

function init() {
  carregarVeiculosAguardando();
  carregarVeiculosLavando();
  carregarVeiculosFinalizado();
}

init();
