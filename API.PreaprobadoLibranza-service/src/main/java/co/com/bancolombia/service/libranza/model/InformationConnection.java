package co.com.bancolombia.service.libranza.model;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * InformationConnection
 */
//@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-21T14:18:14.109-05:00")
public class InformationConnection {

	// @JsonProperty("ip")
	@ApiModelProperty(value = "", required = true, position = 2)
	private String ip = null;

	// @JsonProperty("lastConnection")
	@ApiModelProperty(value = "", required = true, position = 1, example = "")
	private String lastConnection = null;

	// @ApiModelProperty(required = true, value = "")
	@NotNull
	public InformationConnection ip(String ip) {
		this.ip = ip;
		return this;
	}

	/**
	 * Get ip
	 * 
	 * @return ip
	 **/
	// @ApiModelProperty(required = true, value = "")
	@NotNull
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public InformationConnection lastConnection(String lastConnection) {
		this.lastConnection = lastConnection;
		return this;
	}

	/**
	 * Get lastConnection
	 * 
	 * @return lastConnection
	 **/
	// @ApiModelProperty(required = true, value = "")
	@NotNull
	public String getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(String lastConnection) {
		this.lastConnection = lastConnection;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		InformationConnection informationConnection = (InformationConnection) o;
		return Objects.equals(this.ip, informationConnection.ip)
				&& Objects.equals(this.lastConnection, informationConnection.lastConnection);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ip, lastConnection);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class InformationConnection {\n");
		sb.append("    ip: ").append(toIndentedString(ip)).append("\n");
		sb.append("    lastConnection: ").append(toIndentedString(lastConnection)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}