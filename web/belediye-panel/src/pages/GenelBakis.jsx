import { useState, useEffect } from 'react'
import { AreaChart, Area, BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts'

const KAVSAK_NAMES = {
  "0": "Çarşı Kavşağı",
  "1": "Tofaş Kavşağı",
  "2": "İzzet Paşa Kavşağı",
  "3": "Palu Yolu Kavşağı",
  "4": "Üniversite Kavşağı"
}

export default function GenelBakis({ zaman, kavsak }) {
  const [granularity, setGranularity] = useState('dakikalik')
  const [trafikData, setTrafikData] = useState([
    { saat: '00', deger: 120 }, { saat: '02', deger: 90 }, { saat: '04', deger: 70 },
    { saat: '06', deger: 60 }, { saat: '08', deger: 480 }, { saat: '10', deger: 380 },
    { saat: '12', deger: 320 }, { saat: '14', deger: 340 }, { saat: '16', deger: 290 },
    { saat: '18', deger: 520 }, { saat: '20', deger: 280 }, { saat: '22', deger: 160 },
  ])

  const [enerjiData, setEnerjiData] = useState([
    { kavsak: 'Çarşı', deger: 1320 },
    { kavsak: 'Tofaş', deger: 660 },
    { kavsak: 'İzzet P.', deger: 1100 },
    { kavsak: 'Palu', deger: 440 },
    { kavsak: 'Üniversite', deger: 880 },
  ])

  const [metrikler, setMetrikler] = useState([
    { label: 'Ort. Bekleme', value: '28 sn', sub: '✓ Hedef <30 sn', color: '#639922', topColor: '#639922' },
    { label: 'Enerji Tüketimi', value: '4.820 kWh', sub: '↑ +%8 artış', color: '#854F0B', topColor: '#EF9F27' },
    { label: 'Ort. AQI', value: '52.4', sub: '⚠ Orta seviye', color: '#854F0B', topColor: '#EF9F27' },
    { label: 'Müdahale Süresi', value: '3.5 dk', sub: '✓ Hedef <6 dk', color: '#639922', topColor: '#639922' },
    { label: 'Enerji Tasarrufu', value: '-%20', sub: '✓ Hedef karşılandı', color: '#639922', topColor: '#639922' },
    { label: 'Sistem Uptime', value: '%99.9', sub: '✓ Karşılandı', color: '#185FA5', topColor: '#378ADD' },
  ])

  const [kpiler, setKpiler] = useState([
    { label: 'Bekleme süresi', value: '28 sn', target: 'Hedef: <30 sn — %93', percent: 93, color: '#639922' },
    { label: 'Müdahale süresi', value: '3.5 dk', target: 'Hedef: <6 dk — %100', percent: 100, color: '#639922' },
    { label: 'Enerji tasarrufu', value: '-%20', target: 'Hedef: -%20 — %100', percent: 100, color: '#639922' },
    { label: 'Hava kalitesi (AQI)', value: '52.4', target: 'Hedef: <30 — %57', percent: 57, color: '#EF9F27' },
    { label: 'Geçiş kapasitesi', value: '1.100', target: 'Hedef: >1.000 araç/saat — %100', percent: 100, color: '#639922' },
  ])

  const [activeIncident, setActiveIncident] = useState('Ambulans İzzet Paşa → Üniversite güzergahında. Yeşil dalga aktif. Müdahale süresi: 3.5 dk')

  const [prevZaman, setPrevZaman] = useState(zaman)
  if (zaman !== prevZaman) {
    setPrevZaman(zaman)
    if (zaman === 'Bugün') setGranularity('dakikalik')
    else if (zaman === 'Bu hafta') setGranularity('saatlik')
    else setGranularity('gunluk')
  }

  useEffect(() => {
    const fetchData = async () => {
      let readings = []
      try {
        // 1. Fetch sensor readings from PostgreSQL backend
        const res = await fetch('http://127.0.0.1:8000/api/v1/sensors/data?limit=100')
        if (res.ok) {
          const json = await res.json()
          if (json.status === 'success' && json.data && json.data.length > 0) {
            readings = json.data
          }
        }
      } catch (err) {
        console.warn("FastAPI backend connection warning, using simulated fallback:", err)
      }

      // Generate mock/fallback readings if none available
      if (readings.length === 0) {
        const junctions = ['Çarşı', 'Tofaş', 'İzzet Paşa', 'Palu Yolu', 'Üniversite']
        const now = new Date()
        for (let i = 0; i < 40; i++) {
          const date = new Date(now - i * 10 * 1000)
          junctions.forEach((j, idx) => {
            readings.push({
              sensor: { name: `${j} Kavşağı Sensor` },
              recorded_at: date.toISOString(),
              metrics: {
                vehicle_count: Math.round(40 + Math.random() * 140),
                waiting_time: Math.round(15 + Math.random() * 25),
                aqi: Math.round(30 + Math.random() * 35),
                noise_db: Math.round(50 + Math.random() * 25),
                pm25: Math.round(5 + Math.random() * 20)
              }
            })
          })
        }
      }

      try {
        // Separate environment/traffic for the entire dataset first to calculate baseline energy dynamically
        const rawTrafficReadings = readings.filter(r => r.metrics && 'vehicle_count' in r.metrics)
        
        // Apply junction filter if specified
        let filteredReadings = [...readings]
        if (kavsak && kavsak !== "all") {
          const junctionName = KAVSAK_NAMES[kavsak]
          if (junctionName) {
            const prefix = junctionName.split(" Kavşağı")[0]
            filteredReadings = filteredReadings.filter(r => r.sensor && r.sensor.name && r.sensor.name.includes(prefix))
          }
        }
        
        // Filter traffic readings
        const trafficReadings = filteredReadings.filter(r => r.metrics && 'vehicle_count' in r.metrics)
        // Filter environment readings
        const envReadings = filteredReadings.filter(r => r.metrics && 'aqi' in r.metrics)

        // Calculate averages for KPI metrics
        let totalWaitingTime = 0
        let trafficCount = 0
        trafficReadings.forEach(r => {
          if (r.metrics.waiting_time) {
            totalWaitingTime += r.metrics.waiting_time
            trafficCount++
          }
        })
        const avgWait = trafficCount > 0 ? Math.round(totalWaitingTime / trafficCount) : 28

        let totalAqi = 0
        let envCount = 0
        envReadings.forEach(r => {
          if (r.metrics.aqi) {
            totalAqi += r.metrics.aqi
            envCount++
          }
        })
        const avgAqi = envCount > 0 ? (totalAqi / envCount).toFixed(1) : '52.4'

        // Time Period multipliers for energy and averages
        let energyMultiplier = 1
        let statsFlicker = 1.0
        let savingBase = 20.0
        let uptimeBase = 99.92
        let responseTimeBase = 3.5

        if (zaman === 'Bu hafta') {
          energyMultiplier = 7
          statsFlicker = 1.08 + Math.random() * 0.04
          savingBase = 21.2
          uptimeBase = 99.89
          responseTimeBase = 4.1
        } else if (zaman === 'Bu ay') {
          energyMultiplier = 30
          statsFlicker = 0.90 + Math.random() * 0.05
          savingBase = 23.5
          uptimeBase = 99.92
          responseTimeBase = 3.8
        } else if (zaman === 'Bu yıl') {
          energyMultiplier = 365
          statsFlicker = 0.96 + Math.random() * 0.04
          savingBase = 20.8
          uptimeBase = 99.94
          responseTimeBase = 3.5
        } else {
          // Bugün
          statsFlicker = 0.98 + Math.random() * 0.04
          savingBase = 18.4
          uptimeBase = 99.98
          responseTimeBase = 3.2
        }

        // Apply junction differences to dynamic stats
        let junctionWaitBase = 0
        let junctionResponseOffset = 0
        let junctionSavingOffset = 0
        let junctionAqiBase = 0
        let junctionCapacityBase = 1100

        if (kavsak === "0") { // Çarşı
          junctionWaitBase = 7; junctionResponseOffset = -0.4; junctionSavingOffset = -2.3; junctionAqiBase = 15; junctionCapacityBase = 1350
        } else if (kavsak === "1") { // Tofaş
          junctionWaitBase = -8; junctionResponseOffset = 0.6; junctionSavingOffset = 3.0; junctionAqiBase = -8; junctionCapacityBase = 900
        } else if (kavsak === "2") { // İzzet Paşa
          junctionWaitBase = 12; junctionResponseOffset = -0.2; junctionSavingOffset = -1.5; junctionAqiBase = 12; junctionCapacityBase = 1400
        } else if (kavsak === "3") { // Palu Yolu
          junctionWaitBase = -10; junctionResponseOffset = 1.6; junctionSavingOffset = 4.3; junctionAqiBase = -14; junctionCapacityBase = 650
        } else if (kavsak === "4") { // Üniversite
          junctionWaitBase = -2; junctionResponseOffset = -0.1; junctionSavingOffset = 0.5; junctionAqiBase = -5; junctionCapacityBase = 1050
        }

        // Apply time fluctuations to calculated averages
        const finalWait = Math.round((avgWait + junctionWaitBase) * (statsFlicker * 0.9))
        const finalAqi = ((Number(avgAqi) + junctionAqiBase) * (statsFlicker * 0.95)).toFixed(1)

        // Process response time, savings, and uptime
        const finalResponseTime = Math.max(1.5, responseTimeBase + junctionResponseOffset + (Math.random() * 0.4 - 0.2))
        const finalSaving = savingBase + junctionSavingOffset + (Math.random() * 1.2 - 0.6)
        const finalUptime = uptimeBase + (Math.random() * 0.02 - 0.01)
        const finalCapacity = Math.round(junctionCapacityBase * (statsFlicker * 0.95))

        // Process energy consumption (simulate dynamic energy consumption based on vehicle count & noise)
        const energyMap = {
          'Çarşı': 1320,
          'Tofaş': 660,
          'İzzet Paşa': 1100,
          'Palu Yolu': 440,
          'Üniversite': 880
        }
        
        // Let's modify baseline energy using database counts
        rawTrafficReadings.forEach(r => {
          let key = ''
          if (r.sensor && r.sensor.name && r.sensor.name.includes('Çarşı')) key = 'Çarşı'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('Tofaş')) key = 'Tofaş'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('İzzet')) key = 'İzzet Paşa'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('Palu')) key = 'Palu Yolu'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('Üniversite')) key = 'Üniversite'
          
          if (key && r.metrics.vehicle_count) {
            energyMap[key] = Math.round((500 + r.metrics.vehicle_count * 8) * energyMultiplier)
          } else if (key) {
            energyMap[key] = Math.round(energyMap[key] * energyMultiplier)
          }
        })

        const totalEnergy = Object.values(energyMap).reduce((a, b) => a + b, 0)
        
        // Find selected key for display filtering
        const selectedKey = KAVSAK_NAMES[kavsak]?.split(" Kavşağı")[0]
        const displayEnergy = (kavsak && kavsak !== "all" && selectedKey) ? energyMap[selectedKey] : totalEnergy

        // Update metrics state
        setMetrikler([
          { label: 'Ort. Bekleme', value: `${finalWait} sn`, sub: finalWait < 30 ? '✓ Hedef <30 sn' : '⚠ Hedef Aşıldı', color: finalWait < 30 ? '#639922' : '#A32D2D', topColor: finalWait < 30 ? '#639922' : '#E24B4A' },
          { label: 'Enerji Tüketimi', value: `${displayEnergy.toLocaleString('tr-TR')} kWh`, sub: `↑ +%${Math.round(8 * statsFlicker)} artış`, color: '#854F0B', topColor: '#EF9F27' },
          { label: 'Ort. AQI', value: finalAqi, sub: Number(finalAqi) < 50 ? '✓ İyi Seviye' : '⚠ Orta seviye', color: Number(finalAqi) < 50 ? '#639922' : '#854F0B', topColor: Number(finalAqi) < 50 ? '#639922' : '#EF9F27' },
          { label: 'Müdahale Süresi', value: `${finalResponseTime.toFixed(1)} dk`, sub: finalResponseTime < 6.0 ? '✓ Hedef <6 dk' : '⚠ Geliştirilmeli', color: finalResponseTime < 6.0 ? '#639922' : '#A32D2D', topColor: finalResponseTime < 6.0 ? '#639922' : '#E24B4A' },
          { label: 'Enerji Tasarrufu', value: `-%${Math.round(finalSaving)}`, sub: Math.round(finalSaving) >= 20 ? '✓ Hedef karşılandı' : '⚠ Hedef Altında', color: Math.round(finalSaving) >= 20 ? '#639922' : '#854F0B', topColor: Math.round(finalSaving) >= 20 ? '#639922' : '#EF9F27' },
          { label: 'Sistem Uptime', value: `%${finalUptime.toFixed(2)}`, sub: '✓ Karşılandı', color: '#185FA5', topColor: '#378ADD' },
        ])

        // Update KPI bars
        const waitPercent = finalWait < 30 ? 100 : Math.round(30 / finalWait * 100)
        const responsePercent = finalResponseTime < 6.0 ? 100 : Math.round(6.0 / finalResponseTime * 100)
        const savingPercent = Math.min(100, Math.round((finalSaving / 20.0) * 100))
        const aqiValNum = Number(finalAqi)
        const aqiPercent = aqiValNum < 50 ? 100 : Math.min(100, Math.round(50 / aqiValNum * 100))
        const capacityPercent = finalCapacity > 1000 ? 100 : Math.round(finalCapacity / 1000 * 100)

        setKpiler([
          { label: 'Bekleme süresi', value: `${finalWait} sn`, target: `Hedef: <30 sn — %${waitPercent}`, percent: waitPercent, color: finalWait < 30 ? '#639922' : '#E24B4A' },
          { label: 'Müdahale süresi', value: `${finalResponseTime.toFixed(1)} dk`, target: `Hedef: <6 dk — %${responsePercent}`, percent: responsePercent, color: finalResponseTime < 6.0 ? '#639922' : '#E24B4A' },
          { label: 'Enerji tasarrufu', value: `-%${Math.round(finalSaving)}`, target: `Hedef: -%20 — %${savingPercent}`, percent: savingPercent, color: finalSaving >= 20.0 ? '#639922' : '#EF9F27' },
          { label: 'Hava kalitesi (AQI)', value: finalAqi, target: `Hedef: <50 — %${aqiPercent}`, percent: aqiPercent, color: aqiValNum < 50 ? '#639922' : '#EF9F27' },
          { label: 'Geçiş kapasitesi', value: finalCapacity.toLocaleString('tr-TR'), target: `Hedef: >1.000 araç/saat — %${capacityPercent}`, percent: capacityPercent, color: finalCapacity > 1000 ? '#639922' : '#E24B4A' },
        ])

          // Form energy data for bar chart
          const energyEntries = [
            { kavsak: 'Çarşı', deger: energyMap['Çarşı'] },
            { kavsak: 'Tofaş', deger: energyMap['Tofaş'] },
            { kavsak: 'İzzet P.', deger: energyMap['İzzet Paşa'] },
            { kavsak: 'Palu', deger: energyMap['Palu Yolu'] },
            { kavsak: 'Üniversite', deger: energyMap['Üniversite'] },
          ]
          if (kavsak && kavsak !== "all" && selectedKey) {
            setEnerjiData(energyEntries.filter(e => e.kavsak === (selectedKey === 'İzzet Paşa' ? 'İzzet P.' : selectedKey === 'Palu Yolu' ? 'Palu' : selectedKey)))
          } else {
            setEnerjiData(energyEntries)
          }

          // Process traffic readings for chart based on selected local granularity
          let chartData = []
          const avgVol = trafficReadings.reduce((sum, r) => sum + (r.metrics.vehicle_count || 0), 0) / (trafficReadings.length || 1)
          
          if (granularity === 'dakikalik') {
            // Minutely: Show latest 12 readings (real-time stream of 10s intervals)
            const latestTraffic = trafficReadings.slice(0, 12).reverse()
            chartData = latestTraffic.map(r => {
              const date = new Date(r.recorded_at)
              return {
                saat: date.toLocaleTimeString('tr-TR', { hour: '2-digit', minute: '2-digit', second: '2-digit' }),
                deger: Math.round(r.metrics.vehicle_count || 0)
              }
            })
          } else if (granularity === 'saatlik') {
            // Hourly: Show last 12 hours curve based on dynamic DB average
            const hours = ['00:00', '02:00', '04:00', '06:00', '08:00', '10:00', '12:00', '14:00', '16:00', '18:00', '20:00', '22:00']
            const multipliers = [0.4, 0.3, 0.2, 0.5, 1.5, 1.2, 1.0, 1.1, 0.9, 1.6, 1.0, 0.6]
            chartData = hours.map((h, idx) => ({
              saat: h,
              deger: Math.round(avgVol * multipliers[idx])
            }))
          } else if (granularity === 'gunluk') {
            // Daily: Show weekly days based on dynamic DB average
            const gunler = ['Pzt', 'Sal', 'Çar', 'Per', 'Cum', 'Cmt', 'Paz']
            const multipliers = [0.95, 1.0, 1.05, 0.98, 1.15, 0.85, 0.8]
            chartData = gunler.map((g, idx) => ({
              saat: g,
              deger: Math.round(avgVol * multipliers[idx] * 24)
            }))
          }

          if (chartData.length > 0) {
            setTrafikData(chartData)
          }
        } catch (err) {
          console.warn("FastAPI backend connection warning inside try:", err)
        }

        // 2. Fetch incidents to display active alert
        try {
          const incRes = await fetch('http://127.0.0.1:8000/api/v1/incidents?status=active')
          if (incRes.ok) {
            const incidents = await incRes.json()
            if (incidents.length > 0) {
              setActiveIncident(`Aktif Acil Durum: ${incidents[0].title} - ${incidents[0].description}`)
            } else {
              setActiveIncident('Sistem normal durumda. Aktif acil durum ihbarı bulunmuyor.')
            }
          }
        } catch (err) {
          console.warn("Incidents fetch warning:", err)
        }
    }

    fetchData()
    // Poll data every 5 seconds to keep dashboard alive
    const interval = setInterval(fetchData, 5000)
    return () => clearInterval(interval)
  }, [kavsak, zaman, granularity])

  const kavsakName = KAVSAK_NAMES[kavsak] || "Tüm Kavşaklar"
  const trafficChartTitle = zaman === 'Bugün' ? 'Saatlik trafik yoğunluğu' : zaman === 'Bu hafta' ? 'Günlük trafik yoğunluğu' : 'Aylık trafik yoğunluğu'
  const energyChartTitle = zaman === 'Bugün' ? 'Günlük enerji tüketimi' : zaman === 'Bu hafta' ? 'Haftalık enerji tüketimi' : zaman === 'Bu ay' ? 'Aylık enerji tüketimi' : 'Yıllık enerji tüketimi'


  return (
    <div>
      <div className="alert alert-r">
        <span>🚑</span>
        <span><strong>{activeIncident.startsWith('Aktif') ? 'Aktif Olaylar:' : 'Durum Bilgisi:'}</strong> {activeIncident}</span>
      </div>

      <div className="grid5" style={{ gridTemplateColumns: 'repeat(6, 1fr)' }}>
        {metrikler.map((m, i) => (
          <div key={i} className="mc" style={{ borderTopColor: m.topColor }}>
            <div className="mc-label">{m.label}</div>
            <div className="mc-val" style={{ color: m.color }}>{m.value}</div>
            <div className="mc-sub" style={{ color: m.color }}>{m.sub}</div>
          </div>
        ))}
      </div>

      <div className="sec-title">📊 KPI Performansı</div>
      <div className="grid5" style={{ gridTemplateColumns: 'repeat(5, 1fr)' }}>
        {kpiler.map((k, i) => (
          <div key={i} className="kpi">
            <div className="kpi-label">{k.label}</div>
            <div className="kpi-val" style={{ color: k.color }}>{k.value}</div>
            <div className="kpi-target">{k.target}</div>
            <div className="kpi-bar-wrap">
              <div className="kpi-bar" style={{ width: `${k.percent}%`, background: k.color }} />
            </div>
          </div>
        ))}
      </div>

      <div className="grid2">
        <div className="card">
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '8px' }}>
            <div>
              <div className="card-title" style={{ marginBottom: 0 }}>
                {granularity === 'dakikalik' ? 'Dakikalık trafik yoğunluğu' : granularity === 'saatlik' ? 'Saatlik trafik yoğunluğu' : 'Günlük trafik yoğunluğu'}
              </div>
              <div className="card-sub">
                {kavsakName} — {granularity === 'dakikalik' ? 'araç/dakika' : granularity === 'saatlik' ? 'araç/saat' : 'araç/gün'}
              </div>
            </div>
            <select 
              value={granularity} 
              onChange={e => setGranularity(e.target.value)}
              style={{ padding: '4px 8px', borderRadius: '6px', border: '1px solid #334155', fontSize: '11px', background: '#1e293b', color: '#f8fafc', cursor: 'pointer' }}
            >
              <option value="dakikalik">Dakikalık</option>
              <option value="saatlik">Saatlik</option>
              <option value="gunluk">Günlük</option>
            </select>
          </div>
          <ResponsiveContainer width="100%" height={220}>
            <AreaChart data={trafikData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="saat" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} />
              <Tooltip />
              <Area type="monotone" dataKey="deger" stroke="#378ADD" fill="rgba(55,138,221,0.1)" strokeWidth={2} name="Araç/saat" />
            </AreaChart>
          </ResponsiveContainer>
        </div>
        <div className="card">
          <div className="card-title">{energyChartTitle}</div>
          <div className="card-sub">Kavşak bazlı — kWh</div>
          <ResponsiveContainer width="100%" height={220}>
            <BarChart data={enerjiData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="kavsak" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} />
              <Tooltip />
              <Bar dataKey="deger" fill="#378ADD" radius={[4,4,0,0]} name="kWh" />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  )
}
