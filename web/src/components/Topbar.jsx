const PAGE_TITLES = {
  genel: 'Genel Bakış',
  trafik: 'Trafik Yönetimi',
  senaryo: 'Senaryo Simülatörü',
  enerji: 'Enerji Yönetimi',
  cevre: 'Çevre İzleme',
  acil: 'Acil Durum',
  rapor: 'Raporlar & Analiz',
}

const KAVSAK_DATA = {
  genel: { trafik: [42,22,38,18,28], enerji: [1320,660,1100,440,880], aqi: [72.1,45.3,68.4,38.2,42.0], gurultu: [74.2,58.3,71.5,52.1,63.4], pm25: [28.4,14.1,25.2,9.3,15.8] },
}

function exportCSV() {
  const kavsaklar = ['Çarşı Kavşağı','Tofaş Kavşağı','İzzet Paşa Kavşağı','Palu Yolu Kavşağı','Üniversite Kavşağı']
  const trafik = [42,22,38,18,28]
  const enerji = [1320,660,1100,440,880]
  const aqi = [72.1,45.3,68.4,38.2,42.0]
  const gurultu = [74.2,58.3,71.5,52.1,63.4]
  const pm25 = [28.4,14.1,25.2,9.3,15.8]

  let csv = 'Kavşak,Bekleme (sn),Enerji (kWh),AQI,Gürültü (dB),PM2.5 (µg/m³)\n'
  kavsaklar.forEach((k, i) => {
    csv += `${k},${trafik[i]},${enerji[i]},${aqi[i]},${gurultu[i]},${pm25[i]}\n`
  })
  csv += `\nOluşturulma: ${new Date().toLocaleDateString('tr-TR')}\nElazığ Akıllı Şehir — Bayt Sihirbazları 2026\n`

  const blob = new Blob(['\uFEFF' + csv], { type: 'text/csv;charset=utf-8;' })
  const a = document.createElement('a')
  a.href = URL.createObjectURL(blob)
  a.download = 'elazıg_belediye_raporu.csv'
  a.click()
}

function exportPDF() {
  const kavsaklar = ['Çarşı Kavşağı','Tofaş Kavşağı','İzzet Paşa Kavşağı','Palu Yolu Kavşağı','Üniversite Kavşağı']
  const trafik = [42,22,38,18,28]
  const enerji = [1320,660,1100,440,880]
  const aqi = [72.1,45.3,68.4,38.2,42.0]
  const gurultu = [74.2,58.3,71.5,52.1,63.4]
  const pm25 = [28.4,14.1,25.2,9.3,15.8]

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

  html += `<h1>Elazığ Belediyesi — Akıllı Şehir Yönetim Paneli Raporu</h1>`
  html += `<p>Tüm Kavşaklar · ${new Date().toLocaleDateString('tr-TR')}</p>`
  html += `<h2>Kavşak Verileri</h2><table><tr><th>Kavşak</th><th>Bekleme (sn)</th><th>Enerji (kWh)</th><th>AQI</th><th>Gürültü (dB)</th><th>PM2.5</th></tr>`
  kavsaklar.forEach((k, i) => {
    html += `<tr><td>${k}</td><td>${trafik[i]}</td><td>${enerji[i]}</td><td>${aqi[i]}</td><td>${gurultu[i]}</td><td>${pm25[i]}</td></tr>`
  })
  html += `</table><h2>KPI Özeti</h2><table>
    <tr><th>Metrik</th><th>Değer</th><th>Hedef</th><th>Durum</th></tr>
    <tr><td>Bekleme süresi</td><td>28 sn</td><td>&lt;30 sn</td><td>✓ Karşılandı</td></tr>
    <tr><td>Müdahale süresi</td><td>3.5 dk</td><td>&lt;6 dk</td><td>✓ Karşılandı</td></tr>
    <tr><td>Enerji tasarrufu</td><td>-%20</td><td>-%20</td><td>✓ Karşılandı</td></tr>
    <tr><td>AQI</td><td>52.4</td><td>&lt;30</td><td>⚠ Geliştirilmeli</td></tr>
  </table>`
  html += `<div class='footer'>Elazığ Akıllı Şehir Yönetim Sistemi — Bayt Sihirbazları 2026</div></body></html>`

  const w = window.open('', '_blank')
  w.document.write(html)
  w.document.close()
  w.print()
}

export default function Topbar({ zaman, setZaman, kavsak, setKavsak, activePage }) {
  return (
    <div className="topbar">
      <div>
        <div className="page-title">{PAGE_TITLES[activePage]}</div>
        <div className="page-sub">Elazığ Akıllı Şehir — Bayt Sihirbazları 2026</div>
      </div>
      <div className="topbar-right">
        <select value={zaman} onChange={e => setZaman(e.target.value)}>
          <option>Bugün</option>
          <option>Bu hafta</option>
          <option>Bu ay</option>
          <option>Bu yıl</option>
        </select>
        <select value={kavsak} onChange={e => setKavsak(e.target.value)}>
          <option value="all">Tüm kavşaklar</option>
          <option value="0">Çarşı Kavşağı</option>
          <option value="1">Tofaş Kavşağı</option>
          <option value="2">İzzet Paşa Kavşağı</option>
          <option value="3">Palu Yolu Kavşağı</option>
          <option value="4">Üniversite Kavşağı</option>
        </select>
        <button className="btn btn-blue" onClick={exportPDF}>📄 PDF</button>
        <button className="btn btn-green" onClick={exportCSV}>📊 CSV</button>
      </div>
    </div>
  )
}
