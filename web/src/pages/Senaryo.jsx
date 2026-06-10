import { useState, useEffect, useRef } from 'react'
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Cell } from 'recharts'

const KAVSAK_NAMES = {
  "0": "Çarşı Kavşağı",
  "1": "Tofaş Kavşağı",
  "2": "İzzet Paşa Kavşağı",
  "3": "Palu Yolu Kavşağı",
  "4": "Üniversite Kavşağı"
}

export default function Senaryo({ zaman, kavsak }) {
  // Dynamic multipliers based on selected junction
  let kavsakMult = 1.0
  let capMult = 1.0
  if (kavsak === "0") { kavsakMult = 1.25; capMult = 1.20 }
  else if (kavsak === "1") { kavsakMult = 0.80; capMult = 0.85 }
  else if (kavsak === "2") { kavsakMult = 1.40; capMult = 1.15 }
  else if (kavsak === "3") { kavsakMult = 0.60; capMult = 0.55 }
  else if (kavsak === "4") { kavsakMult = 0.95; capMult = 0.90 }

  // Dynamic multipliers based on time periods
  let zamanMult = 1.0
  let zamanCapMult = 1.0
  if (zaman === 'Bu hafta') { zamanMult = 1.08; zamanCapMult = 1.05 }
  else if (zaman === 'Bu ay') { zamanMult = 0.90; zamanCapMult = 0.98 }
  else if (zaman === 'Bu yıl') { zamanMult = 0.96; zamanCapMult = 0.95 }

  const getDynamicBekleme = (base) => {
    if (base === 0) return 0
    return Math.round(base * kavsakMult * zamanMult)
  }

  const getDynamicCap = (baseVal, unitStr) => {
    if (typeof baseVal === 'string') return baseVal
    const val = Math.round(baseVal * capMult * zamanCapMult)
    return `${val.toLocaleString('tr-TR')}${unitStr}`
  }

  const dynamicNormalBekleme = getDynamicBekleme(28)
  const dynamicYogunBekleme = getDynamicBekleme(72)
  const dynamicKazaBekleme = getDynamicBekleme(95)
  const dynamicYolBekleme = getDynamicBekleme(80)
  const dynamicAcilBekleme = getDynamicBekleme(5)
  const dynamicYagmurBekleme = getDynamicBekleme(38)

  const dynamicNormalCap = getDynamicCap(1100, ' araç/saat')
  const dynamicYogunCap = getDynamicCap(950, ' araç/saat')
  const dynamicKazaCap = getDynamicCap(600, ' araç/saat')
  const dynamicYolCap = getDynamicCap(700, ' araç/saat')
  const dynamicAcilCap = getDynamicCap(1150, ' araç/saat')
  const dynamicYagmurCap = getDynamicCap(850, ' araç/saat')

  const SENARYOLAR = {
    normal: { ad: 'Normal trafik', bekleme: `${dynamicNormalBekleme} saniye`, kapasite: dynamicNormalCap, mod: 'Standart optimizasyon', tepki: '✓ Normal', iy: 'Baz değer', cls: 'badge-g' },
    yogun: { ad: 'Yoğun saat', bekleme: `${dynamicYogunBekleme} saniye`, kapasite: dynamicYogunCap, mod: `Rush hour modu (+%${Math.round(40 * kavsakMult)})`, tepki: '⚠ Aktif', iy: `-%${Math.round(40 * zamanMult)} bekleme süresi`, cls: 'badge-w' },
    kaza: { ad: 'Kaza', bekleme: `${dynamicKazaBekleme} saniye`, kapasite: dynamicKazaCap, mod: 'Kaza modu — yönlendirme aktif', tepki: '⚠ Alarm! Rota önerildi', iy: 'Yönlendirme aktif', cls: 'badge-r' },
    yol: { ad: 'Yol çalışması', bekleme: `${dynamicYolBekleme} saniye`, kapasite: dynamicYolCap, mod: 'Yol çalışması modu', tepki: '⚠ 1 şerit kapalı', iy: 'Alternatif güzergah', cls: 'badge-r' },
    acil: { ad: 'Acil araç', bekleme: `${dynamicAcilBekleme} saniye (geçiş önceliği)`, kapasite: dynamicAcilCap, mod: 'KRİTİK — Yeşil dalga', tepki: '✓ Rota temizlendi', iy: `-%${Math.round(56 * zamanMult)} müdahale süresi`, cls: 'badge-b' },
    yagmur: { ad: 'Kötü hava', bekleme: `${dynamicYagmurBekleme} saniye`, kapasite: dynamicYagmurCap, mod: `Güvenli mod -%${Math.round(20 * zamanMult)}`, tepki: `Kaza riski -%${Math.round(35 * kavsakMult)}`, iy: 'Güvenlik öncelikli', cls: 'badge-w' },
  }

  const karsilastirmaData = [
    { name: 'Normal', bekleme: dynamicNormalBekleme, color: '#639922' },
    { name: 'Yoğun', bekleme: dynamicYogunBekleme, color: '#EF9F27' },
    { name: 'Kaza', bekleme: dynamicKazaBekleme, color: '#E24B4A' },
    { name: 'Yol Çalış.', bekleme: dynamicYolBekleme, color: '#E24B4A' },
    { name: 'Acil Araç', bekleme: dynamicAcilBekleme, color: '#378ADD' },
    { name: 'Kötü Hava', bekleme: dynamicYagmurBekleme, color: '#EF9F27' },
  ]

  const TABLO = [
    { senaryo: 'Normal', bekleme: `${dynamicNormalBekleme} sn`, kapasite: getDynamicCap(1100, '/saat'), iy: 'Baz', durum: 'badge-g', durumText: 'İyi' },
    { senaryo: 'Yoğun Saat', bekleme: `${dynamicYogunBekleme} sn`, kapasite: getDynamicCap(950, '/saat'), iy: `-%${Math.round(40 * zamanMult)}`, durum: 'badge-w', durumText: 'Orta' },
    { senaryo: 'Kaza', bekleme: `${dynamicKazaBekleme} sn`, kapasite: getDynamicCap(600, '/saat'), iy: 'Yönlendirme', durum: 'badge-r', durumText: 'Kritik' },
    { senaryo: 'Yol Çalışması', bekleme: `${dynamicYolBekleme} sn`, kapasite: getDynamicCap(700, '/saat'), iy: 'Alternatif', durum: 'badge-r', durumText: 'Kritik' },
    { senaryo: 'Acil Araç', bekleme: `${dynamicAcilBekleme} sn`, kapasite: getDynamicCap(1150, '/saat'), iy: `-%${Math.round(56 * zamanMult)}`, durum: 'badge-b', durumText: 'Aktif' },
    { senaryo: 'Kötü Hava', bekleme: `${dynamicYagmurBekleme} sn`, kapasite: getDynamicCap(850, '/saat'), iy: `Kaza -%${Math.round(35 * kavsakMult)}`, durum: 'badge-w', durumText: 'Orta' },
  ]

  const [aktif, setAktif] = useState('normal')
  const [activeIncident, setActiveIncident] = useState(null)
  
  const userOverrideRef = useRef(false)
  const overrideTimerRef = useRef(null)

  const s = SENARYOLAR[aktif]

  const handleSelectScenario = (id) => {
    setAktif(id)
    
    // Pause auto-switching for 30s when user manually clicks
    userOverrideRef.current = true
    if (overrideTimerRef.current) clearTimeout(overrideTimerRef.current)
    overrideTimerRef.current = setTimeout(() => {
      userOverrideRef.current = false
    }, 30000)
  }

  useEffect(() => {
    const fetchIncidents = async () => {
      try {
        const res = await fetch('http://127.0.0.1:8000/api/v1/incidents?status=active')
        if (res.ok) {
          let incidents = await res.json()
          
          // Filter incidents by selected junction
          const selectedKey = KAVSAK_NAMES[kavsak]?.split(" Kavşağı")[0]
          if (kavsak && kavsak !== "all" && selectedKey) {
            const prefix = selectedKey === 'İzzet Paşa' ? 'İzzet Paşa' : selectedKey === 'Palu Yolu' ? 'Palu Yolu' : selectedKey
            incidents = incidents.filter(inc => (inc.title + " " + inc.description).includes(prefix))
          }

          if (incidents.length > 0) {
            const topInc = incidents[0]
            setActiveIncident(topInc)
            
            // Auto-switch scenario based on active database incident
            if (!userOverrideRef.current) {
              const txt = (topInc.title + " " + topInc.description).toLowerCase()
              if (txt.includes('kaza') || txt.includes('çarpışma')) {
                setAktif('kaza')
              } else if (txt.includes('yol') || txt.includes('bakım') || txt.includes('çalışma')) {
                setAktif('yol')
              } else if (txt.includes('ambulans') || txt.includes('yeşil dalga') || txt.includes('itfaiye') || txt.includes('acil')) {
                setAktif('acil')
              } else if (txt.includes('sıkışıklığı') || txt.includes('yoğunluk') || txt.includes('trafik')) {
                setAktif('yogun')
              } else if (txt.includes('kirlilik') || txt.includes('gürültü') || txt.includes('hava')) {
                setAktif('yagmur')
              }
            }
          } else {
            setActiveIncident(null)
            if (!userOverrideRef.current) {
              setAktif('normal')
            }
          }
        }
      } catch (err) {
        console.warn("Scenario incidents fetch warning:", err)
      }
    }

    fetchIncidents()
    const interval = setInterval(fetchIncidents, 5000)
    return () => {
      clearInterval(interval)
      if (overrideTimerRef.current) clearTimeout(overrideTimerRef.current)
    }
  }, [kavsak, zaman])

  const selectedKavsakName = KAVSAK_NAMES[kavsak] || "Tüm Kavşaklar"

  return (
    <div>
      {activeIncident ? (
        <div className="alert alert-r" style={{ marginBottom: '16px' }}>
          <span>🚨</span>
          <span>
            <strong>Aktif Olay Algılandı ({selectedKavsakName}):</strong> {activeIncident.title} - Algoritma otomatik olarak <strong>{SENARYOLAR[aktif]?.ad}</strong> moduna geçiş yaptı.
          </span>
        </div>
      ) : (
        <div className="alert alert-g">
          <span>ℹ️</span>
          <span>Sistem normal durumda. {kavsak !== 'all' ? <strong>{selectedKavsakName}</strong> : 'Tüm kavşaklar'} üzerinde aktif acil olay bulunmuyor. Senaryoyu manuel test edebilirsiniz.</span>
        </div>
      )}

      <div className="grid2">
        <div className="card">
          <div className="card-title">Senaryo seç ({selectedKavsakName})</div>
          <div className="card-sub">6 farklı trafik senaryosu — gerçek algoritma tepkileri · {zaman}</div>
          <div className="sen-grid">
            {[
              { id: 'normal', icon: '✅', label: 'Normal trafik', cls: '' },
              { id: 'yogun', icon: '⏰', label: 'Yoğun saat', cls: 'warning' },
              { id: 'kaza', icon: '⚠️', label: 'Kaza', cls: 'danger' },
              { id: 'yol', icon: '🔧', label: 'Yol çalışması', cls: 'danger' },
              { id: 'acil', icon: '🚑', label: 'Acil araç', cls: 'danger' },
              { id: 'yagmur', icon: '🌧️', label: 'Kötü hava', cls: 'warning' },
            ].map(btn => (
              <button
                key={btn.id}
                className={`sen-btn ${btn.cls} ${aktif === btn.id ? 'active' : ''}`}
                onClick={() => handleSelectScenario(btn.id)}
              >
                <div style={{ fontSize: 20, marginBottom: 4 }}>{btn.icon}</div>
                {btn.label}
              </button>
            ))}
          </div>

          <div className="sonuc-box">
            {[
              { label: 'Aktif senaryo', val: <span className={`badge ${s.cls}`}>{s.ad}</span> },
              { label: 'Bekleme süresi', val: s.bekleme },
              { label: 'Geçiş kapasitesi', val: s.kapasite },
              { label: 'Algoritma modu', val: s.mod },
              { label: 'Sistem tepkisi', val: s.tepki },
              { label: 'İyileşme oranı', val: s.iy },
            ].map((row, i) => (
              <div key={i} className="sonuc-row">
                <span className="sonuc-label">{row.label}</span>
                <span className="sonuc-val">{row.val}</span>
              </div>
            ))}
          </div>
        </div>

        <div className="card">
          <div className="card-title">Senaryo karşılaştırması ({selectedKavsakName})</div>
          <div className="card-sub">Tüm senaryolar için bekleme süresi (saniye) · {zaman}</div>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={karsilastirmaData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="name" tick={{ fontSize: 9 }} interval={0} />
              <YAxis tick={{ fontSize: 11 }} label={{ value: 'saniye', angle: -90, position: 'insideLeft', fontSize: 11 }} />
              <Tooltip />
              <Bar dataKey="bekleme" radius={[4,4,0,0]} name="Bekleme (sn)">
                {karsilastirmaData.map((entry, index) => (
                  <Cell key={index} fill={entry.color} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>

      <div className="card">
        <div className="card-title">Tüm senaryoların detaylı karşılaştırması ({selectedKavsakName})</div>
        <div className="card-sub">Algoritmanın her senaryodaki performansı · {zaman}</div>
        <table className="tablo">
          <thead>
            <tr>
              <th>Senaryo</th>
              <th>Bekleme Süresi</th>
              <th>Kapasite</th>
              <th>İyileşme</th>
              <th>Durum</th>
            </tr>
          </thead>
          <tbody>
            {TABLO.map((row, i) => (
              <tr key={i}>
                <td>{row.senaryo}</td>
                <td>{row.bekleme}</td>
                <td>{row.kapasite}</td>
                <td>{row.iy}</td>
                <td><span className={`badge ${row.durum}`}>{row.durumText}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
