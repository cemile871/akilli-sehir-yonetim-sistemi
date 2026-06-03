import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Cell } from 'recharts'

const aqiData = [
  { kavsak: 'Çarşı', deger: 72.1, color: '#E24B4A' },
  { kavsak: 'Tofaş', deger: 45.3, color: '#EF9F27' },
  { kavsak: 'İzzet Paşa', deger: 68.4, color: '#E24B4A' },
  { kavsak: 'Palu Yolu', deger: 38.2, color: '#639922' },
  { kavsak: 'Üniversite', deger: 42.0, color: '#EF9F27' },
]

const gurultuData = [
  { kavsak: 'Çarşı', deger: 74.2, color: '#E24B4A' },
  { kavsak: 'Tofaş', deger: 58.3, color: '#639922' },
  { kavsak: 'İzzet Paşa', deger: 71.5, color: '#E24B4A' },
  { kavsak: 'Palu Yolu', deger: 52.1, color: '#639922' },
  { kavsak: 'Üniversite', deger: 63.4, color: '#EF9F27' },
]

const pm25Data = [
  { kavsak: 'Çarşı', deger: 28.4, color: '#EF9F27' },
  { kavsak: 'Tofaş', deger: 14.1, color: '#EF9F27' },
  { kavsak: 'İzzet Paşa', deger: 25.2, color: '#EF9F27' },
  { kavsak: 'Palu Yolu', deger: 9.3, color: '#639922' },
  { kavsak: 'Üniversite', deger: 15.8, color: '#EF9F27' },
]

function RenkliBar({ data, dataKey, domain, label, unit }) {
  return (
    <ResponsiveContainer width="100%" height={220}>
      <BarChart data={data}>
        <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
        <XAxis dataKey="kavsak" tick={{ fontSize: 11 }} />
        <YAxis tick={{ fontSize: 11 }} domain={domain} label={{ value: unit, angle: -90, position: 'insideLeft', fontSize: 11 }} />
        <Tooltip formatter={(val) => [`${val} ${unit}`, label]} />
        <Bar dataKey={dataKey} radius={[4,4,0,0]} name={label}>
          {data.map((entry, index) => (
            <Cell key={index} fill={entry.color} />
          ))}
        </Bar>
      </BarChart>
    </ResponsiveContainer>
  )
}

export default function Cevre() {
  return (
    <div>
      <div className="grid2">
        <div className="card">
          <div className="card-title">Hava kalitesi indeksi (AQI)</div>
          <div className="card-sub">Kavşak bazlı — düşük = iyi (0-100)</div>
          <div className="leg">
            <span><span className="ld" style={{ background: '#639922' }} />İyi &lt;30</span>
            <span><span className="ld" style={{ background: '#EF9F27' }} />Orta 30-60</span>
            <span><span className="ld" style={{ background: '#E24B4A' }} />Kötü &gt;60</span>
          </div>
          <RenkliBar data={aqiData} dataKey="deger" domain={[0, 100]} label="AQI" unit="" />
        </div>
        <div className="card">
          <div className="card-title">Gürültü seviyeleri</div>
          <div className="card-sub">Kavşak bazlı ortalama — rahatsız edici: &gt;65 dB</div>
          <RenkliBar data={gurultuData} dataKey="deger" domain={[40, 90]} label="Gürültü" unit="dB" />
        </div>
      </div>
      <div className="card">
        <div className="card-title">PM2.5 partikül madde dağılımı</div>
        <div className="card-sub">Kavşak bazlı µg/m³ — güvenli sınır: &lt;12 µg/m³</div>
        <div className="leg">
          <span><span className="ld" style={{ background: '#639922' }} />Güvenli &lt;12</span>
          <span><span className="ld" style={{ background: '#EF9F27' }} />Orta 12-35</span>
          <span><span className="ld" style={{ background: '#E24B4A' }} />Yüksek &gt;35</span>
        </div>
        <RenkliBar data={pm25Data} dataKey="deger" domain={[0, 40]} label="PM2.5" unit="µg/m³" />
      </div>
    </div>
  )
}
