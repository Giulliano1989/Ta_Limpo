const GLOBAL_URL = "/api/veiculos";
async function carregarVeiculosAguardando() {
  const response = await fetch(`${GLOBAL_URL}/status/aguardando`);
  const data = await response.json();

  listarVeiculos(
    data,
    document.querySelector("#lista_aguardando"),
    "Iniciar lavagem",
    "lavando",
  );
}

async function carregarVeiculosLavando() {
  const response = await fetch(`${GLOBAL_URL}/status/lavando`);
  const data = await response.json();

  listarVeiculos(
    data,
    document.querySelector("#lista_lavando"),
    "Finalizar lavagem",
    "finalizado",
  );
}

async function carregarVeiculosFinalizado() {
  const response = await fetch(`${GLOBAL_URL}/status/finalizado`);
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
    const response = await fetch(
      `${GLOBAL_URL}/${id}/status?status=${encodeURIComponent(status)}`,
      {
        method: "PATCH",
      },
    );

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
