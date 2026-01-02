# Aero Dynamics Weather Widget

## Company Choice
**Aero Dynamics** was selected for its focus on aviation technology, safety, and precision. This widget is designed for pilots and aviation professionals who need critical weather data for flight planning and safety.

## Design Rationale

### Color Palette
- **Primary:** Navy Blue (#0a1a2a) - Represents aviation professionalism and reliability
- **Secondary:** Safety Orange (#f57c00) - Used in aviation for warnings and critical indicators
- **Accent:** Slate Gray (#2c3e50) - Provides technical, instrument-panel aesthetics

### Typography
- **Headings:** Roboto Condensed - Technical, space-efficient, aviation-inspired
- **Body Text:** Open Sans - Highly legible, clean, and reliable for reading data

### Layout
The layout prioritizes critical flight information:
1. Top: Company branding and city input
2. Center: Temperature, condition, aircraft shape, and flight-critical data
3. Bottom: 3-day forecast for planning

## Technical Implementation
- Built with JavaFX using BorderPane as root layout
- External CSS for complete styling separation
- Property binding to disable refresh button when city field is empty
- Subtle aircraft shape animation mimicking instrument movement

## Reflection

### 1. Brand Alignment
The design reflects Aero Dynamics' values through:
- Technical color scheme (aviation navy and safety orange)
- Clean, uncluttered layout prioritizing critical data
- Flight-specific metrics (wind, visibility, flight conditions)
- Professional typography reminiscent of cockpit instruments

### 2. CSS Architecture Importance
External CSS is crucial for:
- **Brand consistency** - Easy to maintain color schemes and typography
- **Reusability** - Same Java code can be restyled for different companies
- **Separation of concerns** - Designers can work on CSS without touching Java code
- **Performance** - Cached CSS files load faster than inline styles

### 3. Integration Challenge
The most challenging aspect was balancing technical aviation aesthetics with user-friendly design. Creating an interface that feels like professional aviation equipment while remaining intuitive for users required careful consideration of information hierarchy and visual cues. The aircraft shape animation needed to be subtle enough not to distract from critical weather data.



