import { useState, useEffect } from 'react'
import { AreaChart, Area, PieChart, Pie, Cell, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts'

const KAVSAK_NAMES = {
  "0": "Çarşı Kavşağı",
  "1": "Tofaş Kavşağı",
  "2": "İzzet Paşa Kavşağı",
  "3": "Palu Yolu Kavşağı",
  "4": "Üniversite Kavşağı"
}

const BASELINE_KAVSAKLAR = [
  { id: 't1', keyName: 'Çarşı', label: 'Çarşı Kavşağı', baseVal: 1320 },
  { id: 't2', keyName: 'Tofaş', label: 'Tofaş Kavşağı', baseVal: 660 },
  { id: 't3', keyName: 'İzzet Paşa', label: 'İzzet Paşa Kavşağı', baseVal: 1100 },
  { id: 't4', keyName: 'Palu Yolu', label: 'Palu Yolu Kavşağı', baseVal: 440 },
  { id: 't5', keyName: 'Üniversite', label: 'Üniversite Kavşağı', baseVal: 880 },
]

export default function Enerji({ zaman, kavsak }) {
  const [toggles, setToggles] = useState({ t1: true, t2: true, t3: true, t4: true, t5: true })
  
  const [kavsaklarList, setKavsaklarList] = useState([
    { id: 't1', label: 'Çarşı Kavşağı', sub: '1.320 kWh/gün · Normal' },
    { id: 't2', label: 'Tofaş Kavşağı', sub: '660 kWh/gün · Normal' },
    { id: 't3', label: 'İzzet Paşa Kavşağı', sub: '1.100 kWh/gün · Normal' },
    { id: 't4', label: 'Palu Yolu Kavşağı', sub: '440 kWh/gün · Normal' },
    { id: 't5', label: 'Üniversite Kavşağı', sub: '880 kWh/gün · Normal' },
  ])

  const [dagilimData, setDagilimData] = useState([
    { name: 'Aydınlatma', value: 44, color: '#378ADD' },
    { name: 'Kamu Binaları', value: 41, color: '#1D9E75' },
    { name: 'Trafik Sistemleri', value: 15, color: '#E24B4A' },
  ])

  const [trendData, setTrendData] = useState([
    { saat: '00', aydinlatma: 180, binalar: 60, trafik: 30 },
    { saat: '02', aydinlatma: 175, binalar: 55, trafik: 28 },
    { saat: '04', aydinlatma: 170, binalar: 50, trafik: 25 },
    { saat: '06', aydinlatma: 165, binalar: 48, trafik: 22 },
    { saat: '08', aydinlatma: 160, binalar: 45, trafik: 20 },
    { saat: '10', aydinlatma: 155, binalar: 42, trafik: 18 },
    { saat: '12', aydinlatma: 90, binalar: 80, trafik: 35 },
    { saat: '14', aydinlatma: 85, binalar: 120, trafik: 60 },
    { saat: '16', aydinlatma: 80, binalar: 180, trafik: 90 },
    { saat: '18', aydinlatma: 78, binalar: 195, trafik: 95 },
    { saat: '20', aydinlatma: 160, binalar: 150, trafik: 80 },
    { saat: '22', aydinlatma: 175, binalar: 100, trafik: 60 },
  ])

  const tog = id => setToggles(t => ({ ...t, [id]: !t[id] }))

  useEffect(() => {
    const fetchEnergyData = async () => {
      let readings = []
      try {
        const res = await fetch('http://127.0.0.1:8000/api/v1/sensors/data?limit=100')
        if (res.ok) {
          const json = await res.json()
          if (json.status === 'success' && json.data.length > 0) {
            readings = json.data
          }
        }
      } catch (err) {
        console.warn("Energy API warn, using simulated:", err)
      }

      if (readings.length === 0) {
        const junctions = ['Çarşı', 'Tofaş', 'İzzet Paşa', 'Palu Yolu', 'Üniversite']
        const now = new Date()
        for (let i = 0; i < 40; i++) {
          const date = new Date(now - i * 10 * 1000)
          junctions.forEach((j) => {
            readings.push({
              sensor: { name: `${j} Kavşağı Sensor` },
              recorded_at: date.toISOString(),
              metrics: {
                vehicle_count: Math.round(50 + Math.random() * 120),
                waiting_time: Math.round(15 + Math.random() * 20)
              }
            })
          })
        }
      }

      try {
        const trafficReadings = readings.filter(r => r.metrics && 'vehicle_count' in r.metrics)

        // Calculate live energy values for junctions
        const energyMap = {
          'Çarşı': 1320,
          'Tofaş': 660,
          'İzzet Paşa': 1100,
          'Palu Yolu': 440,
          'Üniversite': 880
        }

        trafficReadings.forEach(r => {
          let key = ''
          if (r.sensor && r.sensor.name && r.sensor.name.includes('Çarşı')) key = 'Çarşı'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('Tofaş')) key = 'Tofaş'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('İzzet')) key = 'İzzet Paşa'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('Palu')) key = 'Palu Yolu'
          else if (r.sensor && r.sensor.name && r.sensor.name.includes('Üniversite')) key = 'Üniversite'
          
          if (key && r.metrics.vehicle_count !== undefined) {
            energyMap[key] = Math.round(500 + r.metrics.vehicle_count * 8)
          }
        })

        // Time Period multipliers for energy and averages
        let energyMultiplier = 1
        let aydinlatmaTimeMult = 1.0
        let kamuTimeMult = 1.0
        let trafikTimeMult = 1.0

        if (zaman === 'Bu hafta') {
          energyMultiplier = 7
          aydinlatmaTimeMult = 0.95
          kamuTimeMult = 0.82
          trafikTimeMult = 1.10
        } else if (zaman === 'Bu ay') {
          energyMultiplier = 30
          aydinlatmaTimeMult = 1.14
          kamuTimeMult = 0.88
          trafikTimeMult = 0.96
        } else if (zaman === 'Bu yıl') {
          energyMultiplier = 365
          aydinlatmaTimeMult = 1.06
          kamuTimeMult = 0.91
          trafikTimeMult = 1.02
        } else {
          // Bugün
          energyMultiplier = 1
          aydinlatmaTimeMult = 1.0
          kamuTimeMult = 1.0
          trafikTimeMult = 1.0
        }

        // Compile junctions list based on toggle switches
        const fullList = BASELINE_KAVSAKLAR.map(item => {
          const isActive = toggles[item.id]
          const val = isActive ? energyMap[item.keyName] : 120
          const periodEnergy = Math.round(val * energyMultiplier)
          const statusText = isActive ? 'Normal' : 'Standby / Tasarruf Modu'
          return {
            id: item.id,
            keyName: item.keyName,
            label: item.label,
            sub: `${periodEnergy.toLocaleString('tr-TR')} kWh · ${statusText}`
          }
        })

        // Filter junction control list by selected junction
        let displayList = fullList
        const selectedKey = KAVSAK_NAMES[kavsak]?.split(" Kavşağı")[0]
        if (kavsak && kavsak !== "all" && selectedKey) {
          displayList = fullList.filter(item => item.keyName === (selectedKey === 'İzzet Paşa' ? 'İzzet Paşa' : selectedKey === 'Palu Yolu' ? 'Palu Yolu' : selectedKey))
        }
        setKavsaklarList(displayList)

        // Dynamic Pie Chart distribution
        let totalAydinlatma = 0
        if (kavsak && kavsak !== "all" && selectedKey) {
          const item = BASELINE_KAVSAKLAR.find(x => x.keyName === (selectedKey === 'İzzet Paşa' ? 'İzzet Paşa' : selectedKey === 'Palu Yolu' ? 'Palu Yolu' : selectedKey))
          if (item) {
            totalAydinlatma = toggles[item.id] ? energyMap[item.keyName] : 120
          }
        } else {
          BASELINE_KAVSAKLAR.forEach(item => {
            totalAydinlatma += toggles[item.id] ? energyMap[item.keyName] : 120
          })
        }
        totalAydinlatma = Math.round(totalAydinlatma * energyMultiplier * aydinlatmaTimeMult)

        // Scale static public buildings energy
        let baseKamu = 1500
        if (kavsak && kavsak !== "all" && selectedKey) {
          if (selectedKey === 'Çarşı') baseKamu = 400
          else if (selectedKey === 'Tofaş') baseKamu = 180
          else if (selectedKey === 'İzzet Paşa') baseKamu = 350
          else if (selectedKey === 'Palu Yolu') baseKamu = 120
          else if (selectedKey === 'Üniversite') baseKamu = 250
        }
        const totalKamuBinalari = Math.round(baseKamu * energyMultiplier * kamuTimeMult)
        
        let avgVehicles = 0
        let targetReadings = trafficReadings
        if (kavsak && kavsak !== "all" && selectedKey) {
          const prefix = selectedKey === 'İzzet Paşa' ? 'İzzet Paşa' : selectedKey === 'Palu Yolu' ? 'Palu Yolu' : selectedKey
          targetReadings = trafficReadings.filter(r => r.sensor && r.sensor.name && r.sensor.name.includes(prefix))
        }
        if (targetReadings.length > 0) {
          avgVehicles = targetReadings.reduce((sum, r) => sum + (r.metrics.vehicle_count || 0), 0) / targetReadings.length
        }
        const dailyTrafik = Math.round((avgVehicles > 0 ? avgVehicles : 75) * 8)
        const totalTrafikSistemleri = Math.round(dailyTrafik * energyMultiplier * trafikTimeMult)

        const totalAll = totalAydinlatma + totalKamuBinalari + totalTrafikSistemleri
        const pctAydinlatma = totalAll > 0 ? Math.round((totalAydinlatma / totalAll) * 100) : 44
        const pctKamu = totalAll > 0 ? Math.round((totalKamuBinalari / totalAll) * 100) : 41
        const pctTrafik = Math.max(0, 100 - pctAydinlatma - pctKamu)

        setDagilimData([
          { name: 'Aydınlatma', value: pctAydinlatma, color: '#378ADD' },
          { name: 'Kamu Binaları', value: pctKamu, color: '#1D9E75' },
          { name: 'Trafik Sistemleri', value: pctTrafik, color: '#E24B4A' },
        ])

        // Scale baseline area chart trend data based on toggles
        let activeCount = Object.values(toggles).filter(Boolean).length
        if (kavsak && kavsak !== "all" && selectedKey) {
          const item = BASELINE_KAVSAKLAR.find(x => x.keyName === (selectedKey === 'İzzet Paşa' ? 'İzzet Paşa' : selectedKey === 'Palu Yolu' ? 'Palu Yolu' : selectedKey))
          activeCount = (item && toggles[item.id]) ? 5 : 0
        }
        const aydinlatmaMultiplier = activeCount / 5

        const baseTrend = [
          { saat: '00', aydinlatma: 180, binalar: 60, trafik: 30 },
          { saat: '02', aydinlatma: 175, binalar: 55, trafik: 28 },
          { saat: '04', aydinlatma: 170, binalar: 50, trafik: 25 },
          { saat: '06', aydinlatma: 165, binalar: 48, trafik: 22 },
          { saat: '08', aydinlatma: 160, binalar: 45, trafik: 20 },
          { saat: '10', aydinlatma: 155, binalar: 42, trafik: 18 },
          { saat: '12', aydinlatma: 90, binalar: 80, trafik: 35 },
          { saat: '14', aydinlatma: 85, binalar: 120, trafik: 60 },
          { saat: '16', aydinlatma: 80, binalar: 180, trafik: 90 },
          { saat: '18', aydinlatma: 78, binalar: 195, trafik: 95 },
          { saat: '20', aydinlatma: 160, binalar: 150, trafik: 80 },
          { saat: '22', aydinlatma: 175, binalar: 100, trafik: 60 },
        ]

        setTrendData(baseTrend.map(row => ({
          saat: row.saat,
          aydinlatma: Math.round(((row.aydinlatma * 0.8) * aydinlatmaMultiplier + (row.aydinlatma * 0.2)) * energyMultiplier * aydinlatmaTimeMult),
          binalar: Math.round(row.binalar * energyMultiplier * (kavsak !== "all" ? 0.2 : 1.0) * kamuTimeMult),
          trafik: Math.round(row.trafik * (avgVehicles > 0 ? (avgVehicles / 75) : 1.0) * energyMultiplier * trafikTimeMult)
        })))
      } catch (err) {
        console.warn("Energy calculation error:", err)
      }
    }

    fetchEnergyData()
    const interval = setInterval(fetchEnergyData, 5000)
    return () => clearInterval(interval)
  }, [toggles, zaman, kavsak])

  const selectedKavsakName = KAVSAK_NAMES[kavsak] || "Tüm Kavşaklar"

  const energyChartTitle = 
    zaman === 'Bugün' ? 'Saatlik enerji tüketimi trendi' : 
    zaman === 'Bu hafta' ? 'Haftalık enerji tüketimi trendi' : 
    zaman === 'Bu ay' ? 'Aylık enerji tüketimi trendi' : 'Yıllık enerji tüketimi trendi'

  const energyUnit = zaman === 'Bugün' ? 'kWh' : zaman === 'Bu hafta' ? 'kWh/hafta' : zaman === 'Bu ay' ? 'kWh/ay' : 'kWh/yıl'

  return (
    <div>
      <div className="grid2">
        <div className="card">
          <div className="card-title">Kavşak aydınlatma kontrolü ({selectedKavsakName})</div>
          <div className="card-sub">Aç/kapat ile anlık enerji tasarrufu sağla · {zaman}</div>
          {kavsaklarList.map(k => (
            <div key={k.id} className="kontrol-row">
              <div>
                <div className="kontrol-label">{k.label}</div>
                <div className="kontrol-sub">{k.sub}</div>
              </div>
              <button
                className={`toggle ${toggles[k.id] ? 'on' : 'off'}`}
                onClick={() => tog(k.id)}
                aria-label={k.label}
              />
            </div>
          ))}
          {kavsaklarList.length === 0 && (
            <div style={{ padding: '20px 0', textAlign: 'center', color: '#64748b' }}>Seçili kavşak için aydınlatma kontrolü yüklenemedi.</div>
          )}
        </div>

        <div className="card">
          <div className="card-title">Kaynak bazlı dağılım ({selectedKavsakName})</div>
          <div className="card-sub">Toplam tüketim içindeki pay (%) · {zaman}</div>
          <ResponsiveContainer width="100%" height={280}>
            <PieChart>
              <Pie data={dagilimData} cx="50%" cy="50%" outerRadius={100} innerRadius={60} dataKey="value" nameKey="name" label={({ name, value }) => `${name} %${value}`} labelLine={true}>
                {dagilimData.map((entry, index) => (
                  <Cell key={index} fill={entry.color} />
                ))}
              </Pie>
              <Tooltip formatter={(val) => `%${val}`} />
            </PieChart>
          </ResponsiveContainer>
        </div>
      </div>

      <div className="card">
        <div className="card-title">{energyChartTitle} ({selectedKavsakName})</div>
        <div className="card-sub">Kaynak bazlı 24 saatlik/dönemsel trend ({energyUnit})</div>
        <div className="leg">
          <span><span className="ld" style={{ background: '#378ADD' }} />Aydınlatma</span>
          <span><span className="ld" style={{ background: '#1D9E75' }} />Kamu binaları</span>
          <span><span className="ld" style={{ background: '#E24B4A' }} />Trafik sistemleri</span>
        </div>
        <ResponsiveContainer width="100%" height={220}>
          <AreaChart data={trendData}>
            <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
            <XAxis dataKey="saat" tick={{ fontSize: 11 }} />
            <YAxis tick={{ fontSize: 11 }} />
            <Tooltip />
            <Area type="monotone" dataKey="aydinlatma" stroke="#378ADD" fill="rgba(55,138,221,0.08)" strokeWidth={2} name="Aydınlatma" />
            <Area type="monotone" dataKey="binalar" stroke="#1D9E75" fill="rgba(29,158,117,0.08)" strokeWidth={2} name="Binalar" />
            <Area type="monotone" dataKey="trafik" stroke="#E24B4A" fill="rgba(226,75,74,0.06)" strokeWidth={2} name="Trafik" />
          </AreaChart>
        </ResponsiveContainer>
      </div>
    </div>
  )
}
