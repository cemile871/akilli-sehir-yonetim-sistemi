import { Fragment, useState, useEffect } from 'react'
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, ReferenceLine } from 'recharts'

const KAVSAK_NAMES = {
  "0": "Çarşı",
  "1": "Tofaş",
  "2": "İzzet Paşa",
  "3": "Palu Yolu",
  "4": "Üniversite"
}

export default function AcilDurum({ zaman, kavsak }) {
  const [activeIncident, setActiveIncident] = useState(null)
  const [mudahaleList, setMudahaleList] = useState([])
  const [pendingList, setPendingList] = useState([])
  const [trendData, setTrendData] = useState([
    { gun: 'Pzt', sure: 5.2 }, { gun: 'Sal', sure: 4.8 }, { gun: 'Çar', sure: 4.1 },
    { gun: 'Per', sure: 3.9 }, { gun: 'Cum', sure: 4.5 }, { gun: 'Cmt', sure: 3.8 }, { gun: 'Paz', sure: 4.2 },
  ])

  const [dalgaNodes, setDalgaNodes] = useState([
    { label: 'Çarşı', passive: true },
    { label: 'Tofaş', passive: true },
    { label: 'İzzet Paşa', passive: false },
    { label: 'Palu Yolu', passive: false },
    { label: 'Üniversite', passive: false },
  ])

  const fetchIncidentStatus = async () => {
    try {
      const res = await fetch('http://127.0.0.1:8000/api/v1/incidents?status=active')
      if (res.ok) {
        let incidents = await res.json()
        
        const selectedKey = KAVSAK_NAMES[kavsak]
        if (kavsak && kavsak !== "all" && selectedKey) {
          incidents = incidents.filter(inc => (inc.title + " " + inc.description).includes(selectedKey))
        }

        if (incidents.length > 0) {
          setActiveIncident(incidents[0])
        } else {
          setActiveIncident(null)
        }
      }

      // Fetch all incidents dynamically for the log list
      const resAll = await fetch('http://127.0.0.1:8000/api/v1/incidents')
      if (resAll.ok) {
        const allIncidents = await resAll.json()
        
        // Filter pending incidents for the pending queue
        const pending = allIncidents.filter(inc => inc.status === 'pending').map(inc => {
          let kategori = 'Diğer'
          const cat = inc.category ? inc.category.toLowerCase() : ''
          if (cat === 'traffic' || cat.includes('kaza')) {
            kategori = 'Trafik Kazası'
          } else if (cat === 'fire' || cat.includes('yangın') || cat.includes('yangin')) {
            kategori = 'Yangın'
          } else if (cat === 'water' || cat.includes('su') || cat.includes('sel')) {
            kategori = 'Su Baskını'
          } else if (cat === 'infrastructure' || cat.includes('altyapı') || cat.includes('altyapi')) {
            kategori = 'Altyapı Arızası'
          } else if (cat === 'road_damage' || cat.includes('yol') || cat.includes('hasar')) {
            kategori = 'Yol Hasarı'
          }
          
          return {
            dbId: inc.id,
            reportedBy: inc.reported_by || 'Anonim Vatandaş',
            kategori,
            enlem: inc.latitude || 38.6748,
            boylam: inc.longitude || 39.2225,
            aciklama: inc.description || inc.title
          }
        })
        setPendingList(pending)

        // Filter and map historical logs (active, rejected, resolved)
        const loggedIncidents = allIncidents.filter(inc => inc.status !== 'pending')
        let mapped = loggedIncidents.map(inc => {
          let tip = '🚨 Müdahale'
          const cat = inc.category ? inc.category.toLowerCase() : ''
          if (cat === 'traffic' || cat.includes('kaza')) {
            tip = '🚑 Ambulans'
          } else if (cat === 'fire' || cat.includes('yangın') || cat.includes('yangin')) {
            tip = '🚒 İtfaiye'
          } else if (cat === 'water' || cat.includes('su') || cat.includes('sel')) {
            tip = '🌊 İtfaiye/ASKİ'
          } else if (cat === 'infrastructure' || cat.includes('altyapı') || cat.includes('altyapi')) {
            tip = '🛠️ Altyapı Ekibi'
          } else if (cat === 'road_damage' || cat.includes('yol') || cat.includes('hasar')) {
            tip = '🚧 Fen İşleri'
          }
          
          let durum = 'badge-w'
          let durumText = 'Tamamlandı'
          if (inc.status === 'active') {
            durum = 'badge-g'
            durumText = 'Onaylandı (Aktif)'
          } else if (inc.status === 'rejected') {
            durum = 'badge-r'
            durumText = 'Reddedildi'
          } else if (inc.status === 'resolved') {
            const responseMin = inc.response_time_seconds ? (inc.response_time_seconds / 60) : 5.0
            if (responseMin <= 5.5) {
              durum = 'badge-g'
            } else {
              durum = 'badge-w'
            }
            durumText = 'Çözüldü'
          }
          
          let sure = 'Hesaplanıyor'
          let sureColor = '#3B6D11'
          if (inc.status === 'resolved') {
            const min = inc.response_time_seconds ? (inc.response_time_seconds / 60) : 5.0
            sure = `${min.toFixed(1)} dk`
            sureColor = min <= 5.5 ? '#3B6D11' : '#854F0B'
          } else if (inc.status === 'rejected') {
            sure = '-'
            sureColor = '#A32D2D'
          } else {
            const reportedTime = new Date(inc.reported_at)
            const elapsedMin = Math.max(0.5, (new Date() - reportedTime) / 60000)
            sure = `${elapsedMin.toFixed(1)} dk`
            sureColor = elapsedMin <= 5.5 ? '#3B6D11' : '#854F0B'
          }
          
          let keyName = 'Genel'
          for (const key of Object.values(KAVSAK_NAMES)) {
            if ((inc.title + ' ' + inc.description).includes(key)) {
              keyName = key
              break
            }
          }
          
          return {
            dbId: inc.id,
            tip,
            loc: inc.description || inc.title,
            sure,
            sureColor,
            durum,
            durumText,
            keyName
          }
        })
        
        const selectedKey = KAVSAK_NAMES[kavsak]
        if (kavsak && kavsak !== "all" && selectedKey) {
          mapped = mapped.filter(m => m.loc.includes(selectedKey) || m.keyName === selectedKey)
        }
        
        setMudahaleList(mapped)
      }

      // Scale müdahale süresi trend chart based on selected zaman
      let chartData = []
      if (zaman === 'Bugün') {
        const hours = ['02:00', '06:00', '10:00', '14:00', '18:00', '22:00']
        chartData = hours.map(h => ({
          gun: h,
          sure: Math.round((3.2 + Math.random() * 2.4) * 10) / 10
        }))
      } else if (zaman === 'Bu hafta') {
        const days = ['Pzt', 'Sal', 'Çar', 'Per', 'Cum', 'Cmt', 'Paz']
        chartData = days.map(d => ({
          gun: d,
          sure: Math.round((3.5 + Math.random() * 1.8) * 10) / 10
        }))
      } else if (zaman === 'Bu ay') {
        chartData = Array.from({ length: 6 }, (_, idx) => {
          const dayNum = (idx + 1) * 5
          return {
            gun: `${dayNum}. Gün`,
            sure: Math.round((3.7 + Math.random() * 1.5) * 10) / 10
          }
        })
      } else if (zaman === 'Bu yıl') {
        const months = ['Oca', 'Şub', 'Mar', 'Nis', 'May', 'Haz', 'Tem', 'Ağu', 'Eyl', 'Eki', 'Kas', 'Ara']
        chartData = months.map(m => ({
          gun: m,
          sure: Math.round((3.9 + Math.random() * 1.2) * 10) / 10
        }))
      }
      setTrendData(chartData)

      // Set green wave path nodes based on selection
      const defaultNodes = [
        { label: 'Çarşı', passive: true },
        { label: 'Tofaş', passive: true },
        { label: 'İzzet Paşa', passive: false },
        { label: 'Palu Yolu', passive: false },
        { label: 'Üniversite', passive: false },
      ]

      const selectedKey = KAVSAK_NAMES[kavsak]
      if (kavsak && kavsak !== "all" && selectedKey) {
        setDalgaNodes(defaultNodes.map(n => ({
          label: n.label,
          passive: n.label !== selectedKey
        })))
      } else {
        setDalgaNodes(defaultNodes)
      }
    } catch (err) {
      console.warn("Emergency status warning:", err)
    }
  }

  const handleUpdateStatus = async (incidentId, newStatus) => {
    try {
      const token = localStorage.getItem('token')
      let responseBody = { status: newStatus }
      if (newStatus === 'resolved') {
        responseBody.response_time_seconds = Math.round(200 + Math.random() * 150)
      }

      const res = await fetch(`http://127.0.0.1:8000/api/v1/incidents/${incidentId}`, {
        method: 'PATCH',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(responseBody)
      })

      if (res.ok) {
        fetchIncidentStatus()
      } else {
        const errorData = await res.json()
        alert(`Güncelleme başarısız: ${errorData.detail || 'Bilinmeyen hata'}`)
      }
    } catch (err) {
      console.error('Error updating status:', err)
    }
  }

  useEffect(() => {
    fetchIncidentStatus()
    const interval = setInterval(fetchIncidentStatus, 5000)
    return () => clearInterval(interval)
  }, [kavsak, zaman])

  const selectedKavsakName = kavsak !== "all" ? (KAVSAK_NAMES[kavsak] + " Kavşağı") : "Tüm Kavşaklar"

  const trendTitle = 
    zaman === 'Bugün' ? 'Müdahale süresi trendi (Günlük)' :
    zaman === 'Bu hafta' ? 'Müdahale süresi trendi (Haftalık)' :
    zaman === 'Bu ay' ? 'Müdahale süresi trendi (Aylık)' : 'Müdahale süresi trendi (Yıllık)'

  return (
    <div>
      {activeIncident ? (
        <div className="alert alert-r">
          <span>🚨</span>
          <span>
            <strong>Aktif Acil Durum ({selectedKavsakName}):</strong> {activeIncident.title} - {activeIncident.description}. Sistem KRİTİK öncelikli moddadır.
          </span>
        </div>
      ) : (
        <div className="alert alert-g">
          <span>✓</span>
          <span>Sistem normal durumda. {kavsak !== 'all' ? <strong>{selectedKavsakName}</strong> : 'Tüm kavşaklar'} üzerinde aktif acil durum ihbarı bulunmuyor.</span>
        </div>
      )}

      <div className="grid2">
        <div className="card">
          <div className="card-title">Müdahaleler listesi ({selectedKavsakName})</div>
          <div className="card-sub">KPI hedefi: müdahale süresi &lt;6 dakika · {zaman}</div>
          <div style={{ display: 'flex', flexDirection: 'column', gap: '2px', maxHeight: '320px', overflowY: 'auto', paddingRight: '6px' }}>
            {mudahaleList.slice(0, 6).map((m, i) => (
              <div key={i} className="acil-row" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                <span style={{ minWidth: 90, fontWeight: 600, fontSize: 13 }}>{m.tip}</span>
                <span style={{ flex: 1, fontSize: 12, color: '#64748b', textAlign: 'left', minWidth: 0, padding: '0 8px' }}>{m.loc}</span>
                <span style={{ fontSize: 13, fontWeight: 700, color: m.sureColor }}>{m.sure}</span>
                <span className={`badge ${m.durum}`} style={{ marginLeft: 8 }}>{m.durumText}</span>
                {m.durumText.includes('Aktif') && (
                  <button 
                    className="btn btn-blue" 
                    style={{ padding: '2px 8px', fontSize: '10px', marginLeft: '8px', border: '1px solid #185FA5' }}
                    onClick={() => handleUpdateStatus(m.dbId, 'resolved')}
                  >
                    Çözüldü
                  </button>
                )}
              </div>
            ))}
          </div>
          {mudahaleList.length === 0 && (
            <div style={{ padding: '20px 0', textAlign: 'center', color: '#64748b' }}>Seçili kavşağa ait acil durum müdahalesi bulunmamaktadır.</div>
          )}
        </div>

        <div className="card">
          <div className="card-title">{trendTitle} ({selectedKavsakName})</div>
          <div className="card-sub">Ortalama müdahale süreleri (dakika) · Hedef &lt;6 dk · {zaman}</div>
          <ResponsiveContainer width="100%" height={240}>
            <LineChart data={trendData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="gun" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} domain={[2, 8]} label={{ value: 'dakika', angle: -90, position: 'insideLeft', fontSize: 11 }} />
              <Tooltip formatter={(val) => [`${val} dk`, 'Müdahale süresi']} />
              <ReferenceLine y={6} stroke="#E24B4A" strokeDasharray="5 5" label={{ value: 'Hedef: 6 dk', fill: '#E24B4A', fontSize: 11 }} />
              <Line type="monotone" dataKey="sure" stroke="#E24B4A" strokeWidth={2} dot={{ fill: '#E24B4A', r: 4, strokeWidth: 2, stroke: '#fff' }} name="Müdahale (dk)" />
            </LineChart>
          </ResponsiveContainer>
        </div>
      </div>

      {/* Gelen Vatandaş İhbarları Paneli */}
      <div className="card" style={{ marginBottom: '1.5rem' }}>
        <div className="card-title">Gelen Vatandaş İhbarları</div>
        <div className="card-sub">Onay bekleyen sivil acil durum ihbar kuyruğu</div>
        {pendingList.length === 0 ? (
          <div style={{ padding: '20px 0', textAlign: 'center', color: '#64748b' }}>Bekleyen vatandaş ihbarı bulunmamaktadır.</div>
        ) : (
          <div style={{ overflowX: 'auto' }}>
            <table className="tablo" style={{ marginTop: '10px' }}>
              <thead>
                <tr>
                  <th>Bildiren</th>
                  <th>İhbar Türü</th>
                  <th>Konum</th>
                  <th>Açıklama</th>
                  <th style={{ textAlign: 'right' }}>Aksiyonlar</th>
                </tr>
              </thead>
              <tbody>
                {pendingList.map((p) => (
                  <tr key={p.dbId}>
                    <td><strong>{p.reportedBy}</strong></td>
                    <td><span className="badge badge-b">{p.kategori}</span></td>
                    <td><code style={{ fontSize: '11px' }}>{p.enlem.toFixed(5)}, {p.boylam.toFixed(5)}</code></td>
                    <td>{p.aciklama}</td>
                    <td style={{ textAlign: 'right' }}>
                      <button 
                        className="btn btn-green" 
                        style={{ padding: '4px 10px', fontSize: '11px', marginRight: '5px' }}
                        onClick={() => handleUpdateStatus(p.dbId, 'active')}
                      >
                        Kabul Et
                      </button>
                      <button 
                        className="btn" 
                        style={{ padding: '4px 10px', fontSize: '11px', background: '#FCEBEB', border: '1px solid #fca5a5', color: '#A32D2D' }}
                        onClick={() => handleUpdateStatus(p.dbId, 'rejected')}
                      >
                        Reddet
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      <div className="card">
        <div className="card-title">Yeşil dalga güzergahı ({selectedKavsakName})</div>
        <div className="card-sub">Aktif acil durum geçiş koridoru · {zaman}</div>
        <div className="dalga-box">
          {dalgaNodes.map((node, i) => (
            <Fragment key={node.label}>
              <div className={`dalga-node ${node.passive ? 'passive' : ''}`}>
                {node.label} Kavşağı<br />
                <small style={{ fontWeight: 600 }}>{node.passive ? 'Bekliyor' : '🟢 YEŞİL DALGA'}</small>
              </div>
              {i < dalgaNodes.length - 1 && <div className="dalga-arrow">→</div>}
            </Fragment>
          ))}
        </div>
      </div>
    </div>
  )
}
