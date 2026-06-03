import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, ReferenceLine } from 'recharts'

const mudahaleData = [
  { gun: 'Pzt', sure: 5.2 }, { gun: 'Sal', sure: 4.8 }, { gun: 'Çar', sure: 4.1 },
  { gun: 'Per', sure: 3.9 }, { gun: 'Cum', sure: 4.5 }, { gun: 'Cmt', sure: 3.8 }, { gun: 'Paz', sure: 4.2 },
]

const MUDAHALELER = [
  { tip: '🚑 Ambulans', loc: 'İzzet Paşa → Üniversite', sure: '3.5 dk', suреColor: '#3B6D11', durum: 'badge-g', durumText: '✓ Aktif' },
  { tip: '🚒 İtfaiye', loc: 'Tofaş → Çarşı', sure: '4.2 dk', sureColor: '#3B6D11', durum: 'badge-g', durumText: 'Tamamlandı' },
  { tip: '🚑 Ambulans', loc: 'Palu → İzzet Paşa', sure: '5.1 dk', sureColor: '#3B6D11', durum: 'badge-g', durumText: 'Tamamlandı' },
  { tip: '🚑 Ambulans', loc: 'Çarşı → Üniversite', sure: '5.8 dk', sureColor: '#854F0B', durum: 'badge-w', durumText: 'Tamamlandı' },
]

const DALGA = [
  { label: 'Çarşı', passive: true },
  { label: 'Tofaş', passive: true },
  { label: 'İzzet Paşa', passive: false },
  { label: 'Palu Yolu', passive: false },
  { label: 'Üniversite', passive: false },
]

export default function AcilDurum() {
  return (
    <div>
      <div className="alert alert-r">
        <span>🚨</span>
        <span><strong>Aktif Acil Durum:</strong> Ambulans İzzet Paşa Kavşağı'ndan Üniversite Kavşağı'na ilerliyor. Sistem KRİTİK modda.</span>
      </div>

      <div className="grid2">
        <div className="card">
          <div className="card-title">Son 24 saat müdahaleler</div>
          <div className="card-sub">KPI hedefi: müdahale süresi &lt;6 dakika</div>
          {MUDAHALELER.map((m, i) => (
            <div key={i} className="acil-row">
              <span style={{ minWidth: 90, fontWeight: 600, fontSize: 13 }}>{m.tip}</span>
              <span style={{ flex: 1, fontSize: 12, color: '#64748b' }}>{m.loc}</span>
              <span style={{ fontSize: 13, fontWeight: 700, color: m.suреColor || m.sureColor }}>{m.sure}</span>
              <span className={`badge ${m.durum}`} style={{ marginLeft: 8 }}>{m.durumText}</span>
            </div>
          ))}
        </div>

        <div className="card">
          <div className="card-title">Müdahale süresi trendi</div>
          <div className="card-sub">Son 7 gün ortalama (dakika) — hedef &lt;6 dk</div>
          <ResponsiveContainer width="100%" height={240}>
            <LineChart data={mudahaleData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="gun" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} domain={[3, 7]} label={{ value: 'dakika', angle: -90, position: 'insideLeft', fontSize: 11 }} />
              <Tooltip formatter={(val) => [`${val} dk`, 'Müdahale süresi']} />
              <ReferenceLine y={6} stroke="#E24B4A" strokeDasharray="5 5" label={{ value: 'Hedef: 6 dk', fill: '#E24B4A', fontSize: 11 }} />
              <Line type="monotone" dataKey="sure" stroke="#E24B4A" strokeWidth={2} dot={{ fill: '#E24B4A', r: 4, strokeWidth: 2, stroke: '#fff' }} name="Müdahale (dk)" />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </div>

      <div className="card">
        <div className="card-title">Yeşil dalga güzergahı</div>
        <div className="card-sub">Aktif ambulans güzergahı — İzzet Paşa Kavşağı → Üniversite Kavşağı</div>
        <div className="dalga-box">
          {DALGA.map((node, i) => (
            <>
              <div key={node.label} className={`dalga-node ${node.passive ? 'passive' : ''}`}>
                {node.label}<br />
                <small style={{ fontWeight: 600 }}>{node.passive ? 'Bekliyor' : '🟢 YEŞİL'}</small>
              </div>
              {i < DALGA.length - 1 && <div key={`arrow-${i}`} className="dalga-arrow">→</div>}
            </>
          ))}
        </div>
      </div>
    </div>
  )
}
