import { useState } from 'react'
import { AreaChart, Area, PieChart, Pie, Cell, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Legend } from 'recharts'

const trendData = [
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

const dagilimData = [
  { name: 'Aydınlatma', value: 44, color: '#378ADD' },
  { name: 'Kamu Binaları', value: 41, color: '#1D9E75' },
  { name: 'Trafik Sistemleri', value: 15, color: '#E24B4A' },
]

const KAVSAKLAR = [
  { id: 't1', label: 'Çarşı Kavşağı', sub: '1.320 kWh/gün · ⚠ Anomali tespit edildi' },
  { id: 't2', label: 'Tofaş Kavşağı', sub: '660 kWh/gün · Normal' },
  { id: 't3', label: 'İzzet Paşa Kavşağı', sub: '1.100 kWh/gün · Normal' },
  { id: 't4', label: 'Palu Yolu Kavşağı', sub: '440 kWh/gün · Normal' },
  { id: 't5', label: 'Üniversite Kavşağı', sub: '880 kWh/gün · ⚠ Mesai dışı tüketim' },
]

export default function Enerji() {
  const [toggles, setToggles] = useState({ t1: true, t2: true, t3: true, t4: true, t5: true })
  const tog = id => setToggles(t => ({ ...t, [id]: !t[id] }))

  return (
    <div>
      <div className="grid2">
        <div className="card">
          <div className="card-title">Kavşak aydınlatma kontrolü</div>
          <div className="card-sub">Aç/kapat ile anlık enerji tasarrufu sağla</div>
          {KAVSAKLAR.map(k => (
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
        </div>

        <div className="card">
          <div className="card-title">Kaynak bazlı dağılım</div>
          <div className="card-sub">Toplam tüketim içindeki pay (%)</div>
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
        <div className="card-title">Saatlik enerji tüketimi trendi</div>
        <div className="card-sub">Kaynak bazlı 24 saatlik veri (kWh)</div>
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
