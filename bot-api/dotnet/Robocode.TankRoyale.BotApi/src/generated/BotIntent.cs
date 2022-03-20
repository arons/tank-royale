//----------------------
// <auto-generated>
//     Generated using the NJsonSchema v10.1.2.0 (Newtonsoft.Json v9.0.0.0) (http://NJsonSchema.org)
// </auto-generated>
//----------------------

namespace Robocode.TankRoyale.Schema
{
    #pragma warning disable // Disable all warnings

    /// <summary>Intent from a bot</summary>
    [System.CodeDom.Compiler.GeneratedCode("NJsonSchema", "10.1.2.0 (Newtonsoft.Json v9.0.0.0)")]
    public class BotIntent : Message 
    {
        /// <summary>Turn rate of the body in degrees per turn (can be positive and negative)</summary>
        [Newtonsoft.Json.JsonProperty("turnRate", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public double? TurnRate { get; set; }
    
        /// <summary>Turn rate of the gun in degrees per turn (can be positive and negative)</summary>
        [Newtonsoft.Json.JsonProperty("gunTurnRate", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public double? GunTurnRate { get; set; }
    
        /// <summary>Turn rate of the radar in degrees per turn (can be positive and negative)</summary>
        [Newtonsoft.Json.JsonProperty("radarTurnRate", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public double? RadarTurnRate { get; set; }
    
        /// <summary>New target speed in units per turn (can be positive and negative)</summary>
        [Newtonsoft.Json.JsonProperty("targetSpeed", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public double? TargetSpeed { get; set; }
    
        /// <summary>Attempt to fire gun with the specified firepower</summary>
        [Newtonsoft.Json.JsonProperty("firepower", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.Range(0.0D, 3.0D)]
        public double? Firepower { get; set; }
    
        /// <summary>Flag indicating if the gun must be adjusted to compensate for the body turn.</summary>
        [Newtonsoft.Json.JsonProperty("adjustGunForBodyTurn", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public bool? AdjustGunForBodyTurn { get; set; }
    
        /// <summary>Flag indicating if the radar must be adjusted to compensate for the gun turn.</summary>
        [Newtonsoft.Json.JsonProperty("adjustRadarForGunTurn", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public bool? AdjustRadarForGunTurn { get; set; }
    
        /// <summary>Flag indicating if the bot should rescan with previous radar direction and scan sweep angle.</summary>
        [Newtonsoft.Json.JsonProperty("scan", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        public bool? Scan { get; set; }
    
        /// <summary>New color of the body</summary>
        [Newtonsoft.Json.JsonProperty("bodyColor", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.RegularExpression(@"/^#[0-9A-F]{3,6}$/ig")]
        public string BodyColor { get; set; }
    
        /// <summary>New color of the cannon turret</summary>
        [Newtonsoft.Json.JsonProperty("turretColor", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.RegularExpression(@"/^#[0-9A-F]{3,6}$/ig")]
        public string TurretColor { get; set; }
    
        /// <summary>New color of the radar</summary>
        [Newtonsoft.Json.JsonProperty("radarColor", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.RegularExpression(@"/^#[0-9A-F]{3,6}$/ig")]
        public string RadarColor { get; set; }
    
        /// <summary>New color of the bullet. Note. This will be the color of a bullet when it is fired</summary>
        [Newtonsoft.Json.JsonProperty("bulletColor", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.RegularExpression(@"/^#[0-9A-F]{3,6}$/ig")]
        public string BulletColor { get; set; }
    
        /// <summary>New color of the scan arc</summary>
        [Newtonsoft.Json.JsonProperty("scanColor", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.RegularExpression(@"/^#[0-9A-F]{3,6}$/ig")]
        public string ScanColor { get; set; }
    
        /// <summary>New color of the tracks</summary>
        [Newtonsoft.Json.JsonProperty("tracksColor", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.RegularExpression(@"/^#[0-9A-F]{3,6}$/ig")]
        public string TracksColor { get; set; }
    
        /// <summary>New color of the gun</summary>
        [Newtonsoft.Json.JsonProperty("gunColor", Required = Newtonsoft.Json.Required.DisallowNull, NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore)]
        [System.ComponentModel.DataAnnotations.RegularExpression(@"/^#[0-9A-F]{3,6}$/ig")]
        public string GunColor { get; set; }
    
    
    }
}