//----------------------
// <auto-generated>
//     Generated using the NJsonSchema v10.1.2.0 (Newtonsoft.Json v9.0.0.0) (http://NJsonSchema.org)
// </auto-generated>
//----------------------

namespace Robocode.TankRoyale.Schema
{
    #pragma warning disable // Disable all warnings

    /// <summary>Event occuring when a round has ended.</summary>
    [System.CodeDom.Compiler.GeneratedCode("NJsonSchema", "10.1.2.0 (Newtonsoft.Json v9.0.0.0)")]
    public class RoundEndedEvent : Event 
    {
        /// <summary>Round number</summary>
        [Newtonsoft.Json.JsonProperty("roundNumber", Required = Newtonsoft.Json.Required.Always)]
        public int RoundNumber { get; set; }
    
        /// <summary>Turn number</summary>
        [Newtonsoft.Json.JsonProperty("turnNumber", Required = Newtonsoft.Json.Required.Always)]
        public int TurnNumber { get; set; }
    
    
    }
}