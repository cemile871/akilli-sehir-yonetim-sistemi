import { useState } from 'react'
import { RadarChart, Radar, PolarGrid, PolarAngleAxis, ResponsiveContainer, Tooltip } from 'recharts'

const OZET = [
  { kavsak: 'Çarşı Kavşağı', bekleme: 42, enerji: 1320, aqi: 72.1, gurultu: 74.2, pm25: 28.4, durum: 'badge-r', durumText: 'Kötü' },
  { kavsak: 'Tofaş Kavşağı', bekleme: 22, enerji: 660, aqi: 45.3, gurultu: 58.3, pm25: 14.1, durum: 'badge-w', durumText: 'Orta' },
  { kavsak: 'İzzet Paşa Kavşağı', bekleme: 38, enerji: 1100, aqi: 68.4, gurultu: 71.5, pm25: 25.2, durum: 'badge-r', durumText: 'Kötü' },
  { kavsak: 'Palu Yolu Kavşağı', bekleme: 18, enerji: 440, aqi: 38.2, gurultu: 52.1, pm25: 9.3, durum: 'badge-g', durumText: 'İyi' },
  { kavsak: 'Üniversite Kavşağı', bekleme: 28, enerji: 880, aqi: 42.0, gurultu: 63.4, pm25: 15.8, durum: 'badge-w', durumText: 'Orta' },
]

const radarData = [
  { metrik: 'Trafik', gerceklesen: 93, hedef: 100 },
  { metrik: 'Enerji', gerceklesen: 100, hedef: 100 },
  { metrik: 'Çevre', gerceklesen: 57, hedef: 100 },
  { metrik: 'Acil Durum', gerceklesen: 100, hedef: 100 },
  { metrik: 'Uptime', gerceklesen: 100, hedef: 100 },
]

function exportCSV(zaman, kavsak, modul) {
  let csv = 'Kavşak,Bekleme (sn),Enerji (kWh),AQI,Gürültü (dB),PM2.5 (µg/m³)\n'
  OZET.forEach(r => {
    csv += `${r.kavsak},${r.bekleme},${r.enerji},${r.aqi},${r.gurultu},${r.pm25}\n`
  })
  csv += `\nZaman: ${zaman} · Kavşak: ${kavsak} · Modül: ${modul}\n${new Date().toLocaleDateString('tr-TR')}\nElazığ Akıllı Şehir — Bayt Sihirbazları 2026\n`
  const blob = new Blob(['\uFEFF' + csv], { type: 'text/csv;charset=utf-8;' })
  const a = document.createElement('a')
  a.href = URL.createObjectURL(blob)
  a.download = 'elazıg_belediye_raporu.csv'
  a.click()
}

function exportPDF(zaman, kavsak) {
  let html = `<html><head><meta charset='UTF-8'><style>
    body{font-family:Arial,sans-serif;padding:2rem;color:#1a1a2e}
    h1{font-size:18px;color:#185FA5;margin-bottom:6px}
    h2{font-size:13px;margin:1.5rem 0 .5rem;color:#185FA5;border-bottom:2px solid #E6F1FB;padding-bottom:4px}
    p{font-size:11px;color:#666;margin-bottom:1rem}
    table{width:100%;border-collapse:collapse;font-size:11px;margin-bottom:1rem}
    th{background:#185FA5;color:#fff;padding:7px 10px;text-align:left}
    td{padding:7px 10px;border-bottom:1px solid #eee}
    .footer{margin-top:2rem;font-size:10px;color:#999;border-top:1px solid #eee;padding-top:1rem;text-align:center}
  </style></head><body>`
  html += `<h1>Elazığ Belediyesi — Akıllı Şehir Raporu</h1>`
  html += `<p>${kavsak} · ${zaman} · ${new Date().toLocaleDateString('tr-TR')}</p>`
  html += `<h2>Kavşak Verileri</h2><table><tr><th>Kavşak</th><th>Bekleme</th><th>Enerji</th><th>AQI</th><th>Gürültü</th><th>PM2.5</th></tr>`
  OZET.forEach(r => {
    html += `<tr><td>${r.kavsak}</td><td>${r.bekleme} sn</td><td>${r.enerji} kWh</td><td>${r.aqi}</td><td>${r.gurultu} dB</td><td>${r.pm25}</td></tr>`
  })
  html += `</table><h2>KPI Özeti</h2><table>
    <tr><th>Metrik</th><th>Değer</th><th>Hedef</th><th>Durum</th></tr>
    <tr><td>Bekleme süresi</td><td>28 sn</td><td>&lt;30 sn</td><td>✓ Karşılandı</td></tr>
    <tr><td>Müdahale süresi</td><td>3.5 dk</td><td>&lt;6 dk</td><td>✓ Karşılandı</td></tr>
    <tr><td>Enerji tasarrufu</td><td>-%20</td><td>-%20</td><td>✓ Karşılandı</td></tr>
    <tr><td>AQI</td><td>52.4</td><td>&lt;30</td><td>⚠ Geliştirilmeli</td></tr>
  </table>`
  html += `<div class='footer'>Elazığ Akıllı Şehir — Bayt Sihirbazları 2026</div></body></html>`
  const w = window.open('', '_blank')
  w.document.write(html)
  w.document.close()
  w.print()
}

export default function Raporlar() {
  const [zaman, setZaman] = useState('Bugün')
  const [kavsak, setKavsak] = useState('Tüm kavşaklar')
  const [modul, setModul] = useState('Tümü')

  return (
    <div>
      <div className="card" style={{ marginBottom: 14 }}>
        <div className="card-title">Özel rapor oluştur</div>
        <div className="card-sub">Zaman aralığı, kavşak ve modül seçerek rapor oluştur ve indir</div>
        <div className="rapor-filtre">
          <div>
            <label>Zaman aralığı</label>
            <select value={zaman} onChange={e => setZaman(e.target.value)}>
              <option>Bugün</option><option>Bu hafta</option><option>Bu ay</option><option>Bu yıl</option>
            </select>
          </div>
          <div>
            <label>Kavşak</label>
            <select value={kavsak} onChange={e => setKavsak(e.target.value)}>
              <option>Tüm kavşaklar</option>
              <option>Çarşı Kavşağı</option><option>Tofaş Kavşağı</option>
              <option>İzzet Paşa Kavşağı</option><option>Palu Yolu Kavşağı</option><option>Üniversite Kavşağı</option>
            </select>
          </div>
          <div>
            <label>Modül</label>
            <select value={modul} onChange={e => setModul(e.target.value)}>
              <option>Tümü</option><option>Trafik</option><option>Enerji</option><option>Çevre</option><option>Acil Durum</option>
            </select>
          </div>
        </div>
        <div style={{ display: 'flex', gap: 10, flexWrap: 'wrap' }}>
          <button className="btn btn-blue" style={{ padding: '10px 20px', fontSize: 13 }} onClick={() => exportPDF(zaman, kavsak)}>
            📄 PDF olarak indir
          </button>
          <button className="btn btn-green" style={{ padding: '10px 20px', fontSize: 13 }} onClick={() => exportCSV(zaman, kavsak, modul)}>
            📊 CSV olarak indir
          </button>
        </div>
      </div>

      <div className="card" style={{ marginBottom: 14 }}>
        <div className="card-title">Tüm kavşaklar özet tablosu</div>
        <div className="card-sub">Trafik, enerji, çevre ve genel durum değerlendirmesi</div>
        <table className="tablo">
          <thead>
            <tr>
              <th>Kavşak</th>
              <th>Bekleme (sn)</th>
              <th>Enerji (kWh)</th>
              <th>AQI</th>
              <th>Gürültü (dB)</th>
              <th>PM2.5 (µg/m³)</th>
              <th>Genel Durum</th>
            </tr>
          </thead>
          <tbody>
            {OZET.map((r, i) => (
              <tr key={i}>
                <td><strong>{r.kavsak}</strong></td>
                <td>{r.bekleme}</td>
                <td>{r.enerji}</td>
                <td>{r.aqi}</td>
                <td>{r.gurultu}</td>
                <td>{r.pm25}</td>
                <td><span className={`badge ${r.durum}`}>{r.durumText}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="card">
        <div className="card-title">KPI performans radar grafiği</div>
        <div className="card-sub">Tüm modüllerin hedef gerçekleşme oranları</div>
        <div className="leg">
          <span><span className="ld" style={{ background: '#378ADD' }} />Gerçekleşen</span>
          <span><span className="ld" style={{ background: '#94a3b8' }} />Hedef</span>
        </div>
        <ResponsiveContainer width="100%" height={300}>
          <RadarChart data={radarData}>
            <PolarGrid stroke="#e2e8f0" />
            <PolarAngleAxis dataKey="metrik" tick={{ fontSize: 12 }} />
            <Tooltip />
            <Radar name="Gerçekleşen" dataKey="gerceklesen" stroke="#378ADD" fill="rgba(55,138,221,0.15)" strokeWidth={2} />
            <Radar name="Hedef" dataKey="hedef" stroke="#94a3b8" fill="transparent" strokeWidth={1.5} strokeDasharray="5 5" />
          </RadarChart>
        </ResponsiveContainer>
      </div>
    </div>
  )
}
