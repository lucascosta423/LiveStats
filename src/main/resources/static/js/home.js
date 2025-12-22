function criarLinha(c, dataCampo) {

    let statusLabel = 'Sem status';
    let badgeClass = 'bg-warning';

    if (c.online === 'S') {
        statusLabel = 'Online';
        badgeClass = 'bg-success';
    } else if (c.online === 'N') {
        statusLabel = 'Offline';
        badgeClass = 'bg-error';
    }

    return `
        <tr>
            <td>${c.id_cliente}</td>
            <td>${c.login}</td>
            <td>${c.endereco}</td>
            <td>${dataCampo ? (c[dataCampo] ?? '-') : '-'}</td>
            <td>
                <span class="badge p-5 ${badgeClass}">
                    ${statusLabel}
                </span>
            </td>
        </tr>
    `;
}




function renderTabela({ tbodyId, spanId, dados, label, dataCampo }) {
    const tbody = document.getElementById(tbodyId);
    const span = document.getElementById(spanId);

    tbody.innerHTML = '';
    span.innerHTML = `
        <span>${label.toLowerCase()}:</span>
        <span>${dados.total}</span>
    `;

    dados.logins.forEach(c => {
        tbody.insertAdjacentHTML(
            'beforeend',
            criarLinha(c, dataCampo)
        );
    });
}


async function carregarDashboard() {
    console.log('carregando dashboard...');
    try {
        const res = await fetch('/api/logins');
        if (!res.ok) throw new Error('Erro na API');

        const dados = await res.json();

        renderTabela({
            tbodyId: 'online-body',
            spanId: 'online',
            dados: dados.online,
            label: 'Online',
            dataCampo: 'ultima_conexao_inicial'
        });

        renderTabela({
            tbodyId: 'offline-body',
            spanId: 'offline',
            dados: dados.offline,
            label: 'Offline',
            dataCampo: 'ultima_conexao_final'
        });

        renderTabela({
            tbodyId: 'semstatus-body',
            spanId: 'semstatus',
            dados: dados.semStatus,
            label: 'Sem status'
        });

    } catch (e) {
        console.error(e);
    }
}

carregarDashboard();
setInterval(carregarDashboard, 10000);




