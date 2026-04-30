import React, { useMemo, useState } from 'react';
import { createRoot } from 'react-dom/client';
import './styles.css';

const developers = [
  { id: 'DEV-001', name: 'Ava Chen' },
  { id: 'DEV-002', name: 'Noah Patel' },
  { id: 'DEV-003', name: 'Mia Lopez' },
  { id: 'DEV-004', name: 'Lucas Reed' },
  { id: 'DEV-005', name: 'Emma Roy' },
  { id: 'DEV-006', name: 'Ishan Mehta' },
  { id: 'DEV-007', name: 'Owen Brooks' },
  { id: 'DEV-008', name: 'Zara Khan' },
];

const months = ['2026-03', '2026-04'];

const metricLabels = {
  leadTime: { label: 'Lead Time', suffix: 'days' },
  cycleTime: { label: 'Cycle Time', suffix: 'days' },
  prThroughput: { label: 'PR Throughput', suffix: 'PRs' },
  deploymentFrequency: { label: 'Deployments', suffix: 'deploys' },
  bugRate: { label: 'Bug Rate', suffix: 'bugs' },
};

function App() {
  const [developerId, setDeveloperId] = useState('DEV-002');
  const [month, setMonth] = useState('2026-04');
  const [report, setReport] = useState(null);
  const [status, setStatus] = useState('idle');
  const [error, setError] = useState('');

  const selectedDeveloper = useMemo(
    () => developers.find((developer) => developer.id === developerId),
    [developerId]
  );

  async function handleSubmit(event) {
    event.preventDefault();
    setStatus('loading');
    setError('');

    try {
      const params = new URLSearchParams({ developerId, month });
      const response = await fetch(`http://localhost:8080/api/metrics?${params}`);

      if (!response.ok) {
        throw new Error('No metrics were found for that selection.');
      }

      const data = await response.json();
      setReport(data);
      setStatus('success');
    } catch (requestError) {
      setReport(null);
      setError(requestError.message);
      setStatus('error');
    }
  }

  return (
    <main className="shell">
      <section className="intro">
        <p className="eyebrow">Developer Productivity MVP</p>
        <h1>Monthly delivery health</h1>
      </section>

      <form className="controls" onSubmit={handleSubmit}>
        <label>
          <span>Developer</span>
          <select value={developerId} onChange={(event) => setDeveloperId(event.target.value)}>
            {developers.map((developer) => (
              <option key={developer.id} value={developer.id}>
                {developer.name}
              </option>
            ))}
          </select>
        </label>

        <label>
          <span>Month</span>
          <select value={month} onChange={(event) => setMonth(event.target.value)}>
            {months.map((availableMonth) => (
              <option key={availableMonth} value={availableMonth}>
                {availableMonth}
              </option>
            ))}
          </select>
        </label>

        <button type="submit" disabled={status === 'loading'}>
          {status === 'loading' ? 'Loading...' : 'Get Metrics'}
        </button>
      </form>

      {status === 'idle' && (
        <p className="empty-state">
          Choose a developer and month to view productivity signals for {selectedDeveloper.name}.
        </p>
      )}

      {status === 'error' && <p className="error">{error}</p>}

      {report && (
        <section className="report" aria-live="polite">
          <div className="report-header">
            <div>
              <h2>{report.developerName}</h2>
              <p>
                {report.team} | {report.month}
              </p>
            </div>
          </div>

          <div className="metric-grid">
            {Object.entries(report.metrics).map(([key, value]) => (
              <article className="metric-card" key={key}>
                <span>{metricLabels[key].label}</span>
                <strong>{value}</strong>
                <small>{metricLabels[key].suffix}</small>
              </article>
            ))}
          </div>

          <section className="insight">
            <h3>Interpretation</h3>
            <p>{report.interpretation}</p>
          </section>

          <section className="insight">
            <h3>Next Steps</h3>
            <ul>
              {report.nextSteps.map((step) => (
                <li key={step}>{step}</li>
              ))}
            </ul>
          </section>
        </section>
      )}
    </main>
  );
}

createRoot(document.getElementById('root')).render(<App />);
