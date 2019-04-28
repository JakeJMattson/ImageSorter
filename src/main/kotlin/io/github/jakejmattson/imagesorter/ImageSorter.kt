package io.github.jakejmattson.imagesorter

import java.awt.image.*
import java.io.File
import javax.imageio.ImageIO

fun main() {
	val input = File("input.png")
	val output = File("output.png")
	val image = ImageIO.read(input)!!

	with(image) {
		val pixels = extractPixels().also { it.sort() }

		val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
		newImage.setRGB(0, 0, width, height, pixels, 0, width)

		ImageIO.write(newImage, output.extension, output)
	}
}

private fun BufferedImage.extractPixels() =
	with (BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)) {
		graphics.drawImage(this, 0, 0, null)
		(raster.dataBuffer as DataBufferInt).data
	}