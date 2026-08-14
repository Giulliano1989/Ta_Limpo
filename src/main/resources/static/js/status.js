const GLOBAL_URL = "https://6a56d0efb17de7bebbde7e05.mockapi.io/veiculos";

async function carregarVeiculosAguardando() {
  const url = `${GLOBAL_URL}?status=aguardando`;
  try {
    const response = await fetch(url);
    const data = await response.json();
    const lista = document.querySelector("#lista_aguardando");
    listarAguardando(data, lista);
  } catch (error) {
    return error;
  }
}

async function carregarVeiculosLavando() {
  const url = `${GLOBAL_URL}?status=lavando`;
  try {
    const response = await fetch(url);
    const data = await response.json();
    const lista = document.querySelector("#lista_lavando");
    listarAguardando(data, lista);
  } catch (error) {
    return error;
  }
}

async function carregarVeiculosFinalizado() {
  const url = `${GLOBAL_URL}?status=finalizado`;
  try {
    const response = await fetch(url);
    const data = await response.json();
    const lista = document.querySelector("#lista_finalizado");
    listarAguardando(data, lista);
  } catch (error) {
    return error;
  }
}

function listarAguardando(veiculos, lista) {
  console.log(veiculos);
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

                <button type="button" class="btn btn-primary w-100">
                    Iniciar Lavagem
                </button>
            </div>
        </div>
    `;
  }

  lista.innerHTML = html;
}

function init() {
  carregarVeiculosAguardando();
  carregarVeiculosLavando();
  carregarVeiculosFinalizado();
}

init();
