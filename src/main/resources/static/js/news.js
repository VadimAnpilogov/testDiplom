function openTooltip() {
    var tooltip = document.getElementById("tooltipDiv");

    if(tooltip.style.opacity === "1")
    {
        tooltip.style.opacity = "0";
        setTimeout(tooltip.style.display = "none", 500);
    }
    else
    {
        tooltip.style.display = "block";
        tooltip.style.opacity = "1";
    }
}